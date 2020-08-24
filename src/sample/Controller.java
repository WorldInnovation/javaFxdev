package sample;

import com.alibaba.fastjson.JSON;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.alibaba.fastjson.JSON;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelOut;

    @FXML
    private GridPane usersGrid = new GridPane();

    @FXML
    private TextField requestField;

    @FXML
    private Button getUsers;

    @FXML
    void getUsersActions(ActionEvent event) {
        requestField.setText( String.valueOf("You send request:" + ++count));
        getRequest(REQUEST_LINK);
    }

    @FXML
    void initialize() {

    }

    private void getRequest(String request_link) {
       String requestListWithJsonObject = selectListWithJsonObject(connectService(request_link));
        List<User> users = getUsersFromJsonList(requestListWithJsonObject);

        cleanGrid(usersGrid);
        setGrid(users, usersGrid);
    }

    private void setGrid(List<User> users, GridPane gridPane) {
        int i = 0;
        for (User u : users) {
            Button uSelectButton = new Button(u.getName().getFirst() + " " + u.getName().getLast());
            uSelectButton.setId(gridPane.getId() + i);
            GridPane.setHalignment(uSelectButton, HPos.CENTER);
            gridPane.add(uSelectButton, 1, i);

            String urlImg = u.getPicture().getThumbnail();
            final ImageView imv = new ImageView();
            Image uImage = new Image(urlImg, 100, 45, false, false);
            imv.setImage(uImage);

            final HBox pictureRegion = new HBox();

            pictureRegion.getChildren().add(imv);
            gridPane.add(pictureRegion, 0, i);

            i++;
        }
    }

    private void cleanGrid(GridPane gridPane) {
        List<Node> gridNodeList = gridPane.getChildren();
        for (Node node : gridNodeList) {
            gridPane.clearConstraints(node);
        }
        gridPane.setGridLinesVisible(false);
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);
    }

    private List<User> getUsersFromJsonList(String requestListWithJsonObject) {
        List<User> users = new ArrayList<>();
        try {
            users = JSON.parseArray(requestListWithJsonObject, User.class);
        } catch (Exception e) {
            Alert alertErr = new Alert(Alert.AlertType.ERROR);
            alertErr.setHeaderText("JSON parse error");
            alertErr.setContentText(e.getMessage());
            alertErr.showAndWait();
        }
        return users;
    }
    private String selectListWithJsonObject(StringBuffer sb) {
        Integer firstSymbol = sb.indexOf(START_PARSE_SYMBOL) + 1;
        Integer lastSymbol = sb.lastIndexOf(END_PARSE_SYMBOL);
        return sb.substring(firstSymbol - 1 , lastSymbol + 1 );
    }

    private StringBuffer connectService(String request_link) {
        URL url;
        StringBuffer content = new StringBuffer();
        try{
            url = new URL(request_link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private int count = 0;
    final String REQUEST_LINK = "https://randomuser.me/api/?results=20";
    final String START_PARSE_SYMBOL = "[";
    final String END_PARSE_SYMBOL = "]";
}


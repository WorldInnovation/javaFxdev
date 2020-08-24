package sample;

import com.alibaba.fastjson.JSON;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    @FXML
    private GridPane usersGrid = new GridPane();

    @FXML
    private TextField requestField;

    @FXML
    private Button getUsers;

    @FXML
    void getUsersActions(ActionEvent event) {
        requestField.setText( String.valueOf("You send request:" + ++count));
        getRequest();
    }

    @FXML
    void initialize() {

    }

    private void getRequest() {
       String requestListWithJsonObject = selectListWithJsonObject(connectService());
        List<User> users = getUsersFromJsonList(requestListWithJsonObject);

        cleanGrid(usersGrid);
        setGrid(users, usersGrid);
    }

    private void setGrid(List<User> users, GridPane gridPane) {
        int i = 0;
        for (User u : users) {
            Button uSelectButton = new Button(u.getName().getFirst() + ControllerUtils.WORD_SEPARATOR + u.getName().getLast());
            uSelectButton.setId(gridPane.getId() + i);
            uSelectButton.setOnAction(event -> selectUserButton(u));
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
        for (Node node : gridPane.getChildren()) {
            GridPane.clearConstraints(node);
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
            ControllerUtils.errorAlert(e, ControllerUtils.JSON_PARSE_ERROR);
        }
        return users;
    }
    private String selectListWithJsonObject(StringBuffer sb) {
        Integer firstSymbol = sb.indexOf(ControllerUtils.START_PARSE_SYMBOL) + 1;
        Integer lastSymbol = sb.lastIndexOf(ControllerUtils.END_PARSE_SYMBOL);
        return sb.substring(firstSymbol - 1 , lastSymbol + 1 );
    }

    private StringBuffer connectService() {
        URL url;
        StringBuffer content = new StringBuffer();
        try{
            url = new URL(ControllerUtils.REQUEST_LINK);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (IOException e) {
            ControllerUtils.errorAlert(e, ControllerUtils.CONNECT_ERROR);
        }
        return content;
    }


    private void selectUserButton(User user) {

        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);

        String urlImg = (user.getPicture().getMedium().equals(ControllerUtils.EMPTY_STRING)) ? user.getPicture().getThumbnail()
                : user.getPicture().getMedium();
        final ImageView imv = new ImageView();
        Image uImage = new Image(urlImg, 210, 120, false, false);
        imv.setImage(uImage);

        final HBox pictureRegion = new HBox();
        pictureRegion.getChildren().add(imv);
        grid.add(pictureRegion, 0, 0);

        String gender = (user.getGender().equals(ControllerUtils.EMPTY_STRING)) ? ControllerUtils.EMPTY_FIELD_USER
                : user.getGender();

        String name = user.getName().getFirst() + ControllerUtils.WORD_SEPARATOR + user.getName().getLast();
        name = (user.getName().getFirst().equals(ControllerUtils.WORD_SEPARATOR)) ? ControllerUtils.EMPTY_FIELD_USER
                : name;

        String email = (user.getEmail().equals(ControllerUtils.EMPTY_STRING)) ? ControllerUtils.EMPTY_FIELD_USER
                : user.getEmail();

        String phone = (user.getPhone().equals(ControllerUtils.EMPTY_STRING)) ? ControllerUtils.EMPTY_FIELD_USER
                : user.getPhone();

        String adress = user.getLocation().getCountry() + ControllerUtils.WORD_SEPARATOR + user.getLocation().getState()
                + ControllerUtils.WORD_SEPARATOR + user.getLocation().getCity() + ControllerUtils.WORD_SEPARATOR
                + user.getLocation().getStreet().getName() + ControllerUtils.WORD_SEPARATOR
                + user.getLocation().getStreet().getNumber();
        adress = (adress.equals("   ")) ? user.getLocation().getPostcode()
                : adress;

        Label genderLabel = new Label(ControllerUtils.GENDER_LABEL + gender);
        Label nameLabel = new Label(ControllerUtils.NAME_LABEL + name);
        Label emailLabel = new Label(ControllerUtils.EMAIL_LABEL + email);
        Label phoneLabel = new Label(ControllerUtils.PHONE_LABEL + phone);
        Label addressLabel = new Label(ControllerUtils.ADDRESS_LABEL + adress);

        grid.add(genderLabel, 0, 1);
        grid.add(nameLabel, 0, 2);
        grid.add(emailLabel, 0, 3);
        grid.add(phoneLabel, 0, 4);
        grid.add(addressLabel, 0, 5);

        StackPane root = new StackPane();
        root.getChildren().add(grid);
        Stage stage = new Stage();
        stage.setTitle(ControllerUtils.TITLE_USER_INFO);
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        int vertical = sSize.height / 2;
        int hor = sSize.width / 4;
        stage.setScene(new Scene(root, hor, vertical));
        stage.show();
    }

    private int count = 0;

}


package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelOut;

    @FXML
    private Button getUsers;

    @FXML
    void getUsersActions(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert labelOut != null : "fx:id=\"labelOut\" was not injected: check your FXML file 'sample.fxml'.";
        assert getUsers != null : "fx:id=\"getUsers\" was not injected: check your FXML file 'sample.fxml'.";

    }
}


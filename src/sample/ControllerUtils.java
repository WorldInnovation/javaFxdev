package sample;

import javafx.scene.control.Alert;

class ControllerUtils {
    static final String REQUEST_LINK = "https://randomuser.me/api/?results=20";
    static final String START_PARSE_SYMBOL = "[";
    static final String END_PARSE_SYMBOL = "]";
    static final String JSON_PARSE_ERROR = "JSON parse error";
    static final String EMPTY_FIELD_USER = "INCOGNITO";
    static final String TITLE_USER_INFO = "User info";
    static final String GENDER_LABEL = "GENDER: ";
    static final String NAME_LABEL = "NAME: ";
    static final String EMAIL_LABEL = "EMAIL: ";
    static final String PHONE_LABEL = "PHONE: ";
    static final String ADDRESS_LABEL = "ADDRESS: ";
    static final String WORD_SEPARATOR = " ";
    static final String EMPTY_STRING = "";
    static final String CONNECT_ERROR = "Connect to server error";

    static void errorAlert(Exception e, String headerErr) {
        Alert alertErr = new Alert(Alert.AlertType.ERROR);
        alertErr.setHeaderText(headerErr);
        alertErr.setContentText(e.getMessage());
        alertErr.showAndWait();
    }

}

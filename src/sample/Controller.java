package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Button addButton, getButton;
    @FXML
    private TextField valueField;

    public void switchButtons() {
        addButton.setDisable(!addButton.isDisable());
        getButton.setDisable(!getButton.isDisable());
        valueField.setDisable(!valueField.isDisable());
    }
}

package task1;

import com.sun.webkit.Timer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button addButton, getButton;
    @FXML
    private TextField currencyField, valueField, amountField;
    @FXML
    private Label availableMoney, exceptionLabel;

    public void switchButtons() {
        addButton.setDisable(!addButton.isDisable());
        getButton.setDisable(!getButton.isDisable());
        valueField.setDisable(!valueField.isDisable());
    }

    public void addBanknotes() {
        exceptionLabel.setVisible(false);
        int value, amount, powTen;
        boolean checkLimits = true;
        try {
            if(currencyField.getText().length() != 3) throw new Exception();
            currencyField.setText(currencyField.getText().toUpperCase());
            value = Math.abs(Integer.parseInt(valueField.getText()));
            for (int i = 0; i <= 3; i++) {
                powTen = (int) Math.pow(10, i);
                if(powTen == value || powTen * 5 == value) {
                    checkLimits = false;
                    break;
            }
            }
            if(checkLimits) throw new Exception();
            amount = Math.abs(Integer.parseInt(amountField.getText()));
            String result = Model.addBanknotes(currencyField.getText(), value, amount);
            printMoney(result);
        }
        catch (Exception e) {
            e.printStackTrace();
            exceptionLabel.setVisible(true);
        }
    }

    void printMoney(String s) {
        availableMoney.setText(s);
    }
}

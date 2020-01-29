package task1;

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
    private Label valueGet, amountGet, availableMoney, exceptionLabel, amountLabel;

    public void switchButtons() {
        if(!amountLabel.getText().equals("Amount")) amountLabel.setText("Amount");
        else amountLabel.setText("Money");
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
            for (int i = 0; i < 2; i++) if(currencyField.getText().charAt(i) < 65 || currencyField.getText().charAt(i) > 90)
                throw new Exception();
            currencyField.setText(currencyField.getText());
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

    public void getBanknotes() {
        //TODO: CONTINUE HERE
    }

    private void printMoney(String s) {
        availableMoney.setText(s);
    }
}

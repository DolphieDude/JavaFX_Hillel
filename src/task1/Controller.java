package task1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;
import java.util.function.Predicate;

public class Controller {
    @FXML
    private Button addButton, getButton;
    @FXML
    private TextField currencyField, valueField, amountField;
    @FXML
    private Label valueGet, amountGet, availableMoney, exceptionLabel, moneyExceptionLabel, amountLabel, valueGetLabel, amountGetLabel;

    private Predicate<String> checkIsCurrency = s -> {
        if(s.length() != 3) return false;
        for (int i = 0; i < 2; i++) if(s.charAt(i) < 65 || s.charAt(i) > 90)
            return false;
        return true;
    };

    public void switchButtons() {
        if(!amountLabel.getText().equals("Amount")) amountLabel.setText("Amount");
        else amountLabel.setText("Money");
        addButton.setDisable(!addButton.isDisable());
        getButton.setDisable(!getButton.isDisable());
        valueField.setDisable(!valueField.isDisable());
        valueField.setText("");
    }

    public void addBanknotes() {
        valueGet.setVisible(false);
        amountGet.setVisible(false);
        valueGetLabel.setVisible(false);
        amountGetLabel.setVisible(false);
        exceptionLabel.setVisible(false);
        int value, amount, powTen;
        boolean checkLimits = true;
        try {
            if(!checkIsCurrency.test(currencyField.getText())) throw new Exception();
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
            Model.addBanknotes(currencyField.getText(), value, amount);
            availableMoney.setText(Model.getBanknotesString());
        }
        catch (Exception e) {
            e.printStackTrace();
            exceptionLabel.setVisible(true);
        }
    }

    public void getBanknotes() {
        exceptionLabel.setVisible(false);
        moneyExceptionLabel.setVisible(false);
        valueGet.setVisible(true);
        amountGet.setVisible(true);
        valueGetLabel.setVisible(true);
        amountGetLabel.setVisible(true);
        try {
            int money;
            if(!checkIsCurrency.test(currencyField.getText())) throw new Exception();
            money = Math.abs(Integer.parseInt(amountField.getText()));
            int[] result = Model.getBanknotes(currencyField.getText(), money);
            if(Objects.isNull(result)) {
                moneyExceptionLabel.setVisible(true);
                valueGet.setVisible(false);
                amountGet.setVisible(false);
                valueGetLabel.setVisible(false);
                amountGetLabel.setVisible(false);
                return;
            }
            valueGet.setText(result[0] + "");
            amountGet.setText(result[1] + "");
            availableMoney.setText(Model.getBanknotesString());
        }
        catch (Exception e) {
            e.printStackTrace();
            exceptionLabel.setVisible(true);
        }
    }
}

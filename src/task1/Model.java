package task1;

import java.util.ArrayList;
import java.util.HashMap;

class Model {
    private static HashMap<Banknote, Integer> banknotesMap = new HashMap<>();

    static String getBanknotesString() {
        StringBuilder result = new StringBuilder();
        for (Banknote b: banknotesMap.keySet()) result.
                append(String.format("%s %sVAL %s\n", b.getCurrency(), b.getValue(), banknotesMap.get(b)));
        return result.toString();
    }

    static void addBanknotes(String currency, int value, int amount) {
        Banknote newBanknote = new Banknote(value, currency);
        int amountFromMap;
        try {
            amountFromMap = banknotesMap.get(newBanknote);
        }
        catch (NullPointerException e) {
            amountFromMap = 0;
        }
        banknotesMap.put(newBanknote, amountFromMap + amount);
    }

    static int[] getBanknotes(String currency, int money) {
        ArrayList<Integer> suitableValues = new ArrayList<>();
        for (int i = 3; i >= 0; i--) {
            if(money % Math.pow(10, i) == 0) suitableValues.add((int)Math.pow(10, i));
            if(money % (5 * Math.pow(10, i)) == 0) suitableValues.add((int)(5 * Math.pow(10, i)));
        }
        int amount, neededAmount;
        Banknote newBanknote;
        for (Integer i: suitableValues) {
            try {
                newBanknote = new Banknote(i, currency);
                amount = banknotesMap.get(newBanknote);
                if(amount * i < money) throw new Exception();
            }
            catch (Exception e) {
                continue;
            }
            neededAmount = money / i;
            if(banknotesMap.get(newBanknote) - neededAmount == 0) banknotesMap.remove(newBanknote);
            else banknotesMap.replace(newBanknote, banknotesMap.get(newBanknote) - neededAmount);
            return new int[] {i, neededAmount};
        }
        return null;
    }
}

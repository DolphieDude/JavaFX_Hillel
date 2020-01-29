package task1;

import java.util.HashMap;

class Model {
    private static HashMap<Banknote, Integer> banknotesMap = new HashMap<>();

    static String addBanknotes(String currency, int value, int amount) {
        Banknote newBanknote = new Banknote(value, currency);
        int amountFromMap;
        try {
            amountFromMap = banknotesMap.get(newBanknote);
        }
        catch (NullPointerException e) {
            amountFromMap = 0;
        }
        banknotesMap.put(new Banknote(value, currency), amountFromMap + amount);
        StringBuilder result = new StringBuilder();
        for (Banknote b: banknotesMap.keySet()) result.
                append(String.format("%s %sVAL %s\n", b.getCurrency(), b.getValue(), banknotesMap.get(b)));
        return result.toString();
    }
}

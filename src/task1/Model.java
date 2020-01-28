package task1;

class Model {
    static String addBanknotes(String currency, int value, int amount) {
        return String.format("%s %s %s\n", currency, value, amount);
    }
}

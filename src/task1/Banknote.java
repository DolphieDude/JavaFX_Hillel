package task1;

class Banknote {
    private int value;
    private String currency;

    Banknote(int value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    int getValue() {
        return value;
    }

    String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object obj) { //redundant for now
        if(obj instanceof Banknote) {
            if (obj == this) return true;
            Banknote banknote = (Banknote) obj;
            return banknote.getValue() == this.value && banknote.getCurrency().equals(this.currency);
        }
        else return false;
    }

    @Override
    public int hashCode() {
        char[] currencyChars = this.currency.toCharArray();
        int result = 0;
        int j = 2;
        for (int i = 1; i <= 10_000; i *= 100) {
            result += currencyChars[j] * i;
            j--;
        }
        result += value * 1_000_000;
        return result; //Why not long... But still unique for symbols range
    }
}

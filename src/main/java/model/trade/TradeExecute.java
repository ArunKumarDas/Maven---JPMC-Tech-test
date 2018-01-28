package model.trade;

public enum TradeExecute{
    BUY("B"),
    SELL("S");

    private String text;

    TradeExecute(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static TradeExecute fromString(String text) {

        if (text != null) {
            for (TradeExecute tmp : TradeExecute.values()) {
                if (text.equalsIgnoreCase(tmp.text)) {
                    return tmp;
                }
            }

            throw new IllegalArgumentException("No enumeration constant with text " + text + " found!");
        } else {
            throw new NullPointerException("Null pointer supplied.");
        }
    }
}

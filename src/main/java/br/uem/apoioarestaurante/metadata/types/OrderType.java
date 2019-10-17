package br.uem.apoioarestaurante.metadata.types;

/**
 * @author Maicon
 */
public enum OrderType {
    LOCAL("Local"),
    DELIVERY("Delivery");

    private String textValue;

    OrderType(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }
}

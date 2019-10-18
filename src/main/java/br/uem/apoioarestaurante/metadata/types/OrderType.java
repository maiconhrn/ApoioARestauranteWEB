package br.uem.apoioarestaurante.metadata.types;

/**
 * @author Maicon
 */
public enum OrderType {
    LOCAL("Local"),
    DELIVERY("Delivery"),
    BOTH("Ambos");

    private String textValue;

    OrderType(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }
}

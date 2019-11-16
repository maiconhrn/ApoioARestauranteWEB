package br.uem.apoioarestaurante.metadata.types;

/**
 * @author Maicon
 */
public enum PedidoTipo {
    LOCAL("Local"),
    DELIVERY("Delivery"),
    //only for filter, dont use as a tipo of an Pedido
    BOTH("Ambos");

    private String textValue;

    PedidoTipo(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }
}

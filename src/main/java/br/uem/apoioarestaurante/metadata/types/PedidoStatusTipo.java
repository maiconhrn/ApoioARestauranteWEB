package br.uem.apoioarestaurante.metadata.types;

/**
 * @author Maicon
 */
public enum PedidoStatusTipo {
    OPENED("Aberto"),
    CLOSED("Fechado");

    private String textValue;

    PedidoStatusTipo(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }
}

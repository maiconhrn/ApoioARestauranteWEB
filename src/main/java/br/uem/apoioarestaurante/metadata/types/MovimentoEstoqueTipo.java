package br.uem.apoioarestaurante.metadata.types;

/**
 * @author Maicon
 */
public enum MovimentoEstoqueTipo {
    IN("Entrada"),
    OUT("Saída");

    private String textValue;

    MovimentoEstoqueTipo(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }
}

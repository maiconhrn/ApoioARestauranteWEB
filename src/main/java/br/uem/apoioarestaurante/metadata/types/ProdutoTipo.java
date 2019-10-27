package br.uem.apoioarestaurante.metadata.types;

/**
 * @author Maicon
 */
public enum ProdutoTipo {
    RESALE("Revenda"),
    MANUFACTURED("Manufaturado"),
    FEEDSTOCK("Matéria prima");

    private String textValue;

    ProdutoTipo(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }
}

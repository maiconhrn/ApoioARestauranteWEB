package br.uem.apoioarestaurante.metadata.entities.builder;

import br.uem.apoioarestaurante.metadata.entities.Estoque;
import br.uem.apoioarestaurante.metadata.entities.Produto;

import java.util.Date;

/**
 * @author Maicon
 */
public final class EstoqueBuilder {
    private Produto produto;
    private Integer qtdEmEstoque;
    private Integer qtdMinima;
    private Date ultimaEntrada;
    private Date ultimaSaida;
    private Boolean ativo;
    private Boolean pedidoFeito;

    private EstoqueBuilder() {
    }

    public static EstoqueBuilder anEstoque() {
        return new EstoqueBuilder();
    }

    public EstoqueBuilder withProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public EstoqueBuilder withQtdEmEstoque(Integer qtdEmEstoque) {
        this.qtdEmEstoque = qtdEmEstoque;
        return this;
    }

    public EstoqueBuilder withQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
        return this;
    }

    public EstoqueBuilder withUltimaEntrada(Date ultimaEntrada) {
        this.ultimaEntrada = ultimaEntrada;
        return this;
    }

    public EstoqueBuilder withUltimaSaida(Date ultimaSaida) {
        this.ultimaSaida = ultimaSaida;
        return this;
    }

    public EstoqueBuilder withAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public EstoqueBuilder withPedidoFeito(Boolean pedidoFeito) {
        this.pedidoFeito = pedidoFeito;
        return this;
    }

    public Estoque build() {
        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQtdEmEstoque(qtdEmEstoque);
        estoque.setQtdMinima(qtdMinima);
        estoque.setUltimaEntrada(ultimaEntrada);
        estoque.setUltimaSaida(ultimaSaida);
        estoque.setAtivo(ativo);
        estoque.setPedidoFeito(pedidoFeito);
        return estoque;
    }
}

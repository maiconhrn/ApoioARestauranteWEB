package br.uem.apoioarestaurante.metadata.entities.builder;

import br.uem.apoioarestaurante.metadata.entities.ItemPedido;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.entities.Produto;

/**
 * @author Maicon
 */
public final class ItemPedidoBuilder {
    private Integer qtdProduto;
    private Double preco;
    private Pedido pedido;
    private Produto produto;
    private Boolean ativo;

    private ItemPedidoBuilder() {
    }

    public static ItemPedidoBuilder anItemPedido() {
        return new ItemPedidoBuilder();
    }

    public ItemPedidoBuilder withQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
        return this;
    }

    public ItemPedidoBuilder withPreco(Double preco) {
        this.preco = preco;
        return this;
    }

    public ItemPedidoBuilder withPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public ItemPedidoBuilder withProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public ItemPedidoBuilder withAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public ItemPedido build() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQtdProduto(qtdProduto);
        itemPedido.setPreco(preco);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setAtivo(ativo);
        return itemPedido;
    }
}

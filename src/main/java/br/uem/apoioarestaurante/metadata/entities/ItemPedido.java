package br.uem.apoioarestaurante.metadata.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Maicon
 */
@Entity
@Table(name = "aar_item_pedido")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 6292950162662163343L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qtd_produto")
    private Integer qtdProduto;

    @Column(name = "preco")
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "ativo")
    private Boolean ativo;

    public ItemPedido() {
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(qtdProduto, that.qtdProduto) &&
                Objects.equals(preco, that.preco) &&
                Objects.equals(pedido, that.pedido) &&
                Objects.equals(produto, that.produto) &&
                Objects.equals(ativo, that.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qtdProduto, preco, pedido, produto, ativo);
    }
}

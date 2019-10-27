package br.uem.apoioarestaurante.metadata.entities;

import br.uem.apoioarestaurante.exceptions.EstoqueException;
import br.uem.apoioarestaurante.metadata.types.MovimentoEstoqueTipo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Maicon
 */
@Entity
@Table(name = "aar_estoque")
public class Estoque implements Serializable {

    private static final long serialVersionUID = -943067544577531821L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "qtd_em_estoque")
    private Integer qtdEmEstoque;

    @Column(name = "qtd_minima")
    private Integer qtdMinima;

    @Column(name = "ultima_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaEntrada;

    @Column(name = "ultima_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaSaida;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "pedidoFeito")
    private Boolean pedidoFeito;

    public Estoque() {
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQtdEmEstoque() {
        return qtdEmEstoque;
    }

    public void setQtdEmEstoque(Integer qtdEmEstoque) {
        this.qtdEmEstoque = qtdEmEstoque;
    }

    public Integer getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public Date getUltimaEntrada() {
        return ultimaEntrada;
    }

    public void setUltimaEntrada(Date ultimaEntrada) {
        this.ultimaEntrada = ultimaEntrada;
    }

    public Date getUltimaSaida() {
        return ultimaSaida;
    }

    public void setUltimaSaida(Date ultimaSaida) {
        this.ultimaSaida = ultimaSaida;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getPedidoFeito() {
        return pedidoFeito;
    }

    public void setPedidoFeito(Boolean pedidoFeito) {
        this.pedidoFeito = pedidoFeito;
    }

    public MovimentoEstoque novaMovimentacaoEstoque(MovimentoEstoqueTipo tipo, int qtd, Usuario usuario) throws EstoqueException {
        if (tipo == MovimentoEstoqueTipo.OUT && this.getQtdEmEstoque() <= 0) {
            throw new EstoqueException("Não e possivel fazer baixa no estoque: quantidade de produtos no sistema é 0");
        }

        MovimentoEstoque movimentoEstoque = new MovimentoEstoque();
        movimentoEstoque.setData(new Date());
        movimentoEstoque.setEstoque(this);
        movimentoEstoque.setQtd(qtd);
        movimentoEstoque.setTipo(tipo);
        movimentoEstoque.setUsuario(usuario);

        this.setQtdEmEstoque(tipo == MovimentoEstoqueTipo.IN ? this.getQtdEmEstoque() + qtd : this.getQtdEmEstoque() - qtd);

        return movimentoEstoque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id) &&
                Objects.equals(produto, estoque.produto) &&
                Objects.equals(qtdEmEstoque, estoque.qtdEmEstoque) &&
                Objects.equals(qtdMinima, estoque.qtdMinima) &&
                Objects.equals(ultimaEntrada, estoque.ultimaEntrada) &&
                Objects.equals(ultimaSaida, estoque.ultimaSaida) &&
                Objects.equals(ativo, estoque.ativo) &&
                Objects.equals(pedidoFeito, estoque.pedidoFeito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produto, qtdEmEstoque, qtdMinima, ultimaEntrada, ultimaSaida, ativo, pedidoFeito);
    }
}

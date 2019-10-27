package br.uem.apoioarestaurante.metadata.entities;

import br.uem.apoioarestaurante.metadata.types.PedidoStatusTipo;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Maicon
 */
@Entity
@Table(name = "aar_pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = -7426816774231058297L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private PedidoTipo tipo;

    @Column(name = "total")
    private Double total;

    @Column(name = "data_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Column(name = "data_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;

    @Column(name = "mesa")
    private Integer mesa;

    @Column(name = "balcao")
    private Integer balcao;

    @Column(name = "status")
    private PedidoStatusTipo status;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(nullable = false, name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Where(clause = "ativo = true")
    private List<ItemPedido> items;

    public Pedido() {
        this.items = new ArrayList<>();
        this.ativo = true;
        this.total = 0D;
        this.status = PedidoStatusTipo.OPENED;
    }

    public Long getId() {
        return id;
    }

    public PedidoTipo getTipo() {
        return tipo;
    }

    public void setTipo(PedidoTipo tipo) {
        this.tipo = tipo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public Integer getBalcao() {
        return balcao;
    }

    public void setBalcao(Integer balcao) {
        this.balcao = balcao;
    }

    public PedidoStatusTipo getStatus() {
        return status;
    }

    public void setStatus(PedidoStatusTipo status) {
        this.status = status;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public void refreshTotal() {
        this.setTotal(0D);
        this.getItems().forEach((i) -> {
            i.setPreco(i.getProduto().getPrecoVenda() * i.getQtdProduto());

            this.setTotal(this.getTotal() + i.getPreco());
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) &&
                tipo == pedido.tipo &&
                Objects.equals(total, pedido.total) &&
                Objects.equals(dataInicio, pedido.dataInicio) &&
                Objects.equals(dataFim, pedido.dataFim) &&
                Objects.equals(mesa, pedido.mesa) &&
                Objects.equals(balcao, pedido.balcao) &&
                status == pedido.status &&
                Objects.equals(ativo, pedido.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, total, dataInicio, dataFim, mesa, balcao, status, ativo);
    }
}

package br.uem.apoioarestaurante.metadata.entities.builder;

import br.uem.apoioarestaurante.metadata.entities.Cliente;
import br.uem.apoioarestaurante.metadata.entities.ItemPedido;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.entities.Usuario;
import br.uem.apoioarestaurante.metadata.types.PedidoStatusTipo;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;

import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public final class PedidoBuilder {
    private PedidoTipo tipo;
    private Double total;
    private Date dataInicio;
    private Date dataFim;
    private Integer mesa;
    private Integer balcao;
    private PedidoStatusTipo status;
    private Boolean ativo;
    private Usuario usuario;
    private Cliente cliente;
    private List<ItemPedido> items;

    private PedidoBuilder() {
    }

    public static PedidoBuilder aPedido() {
        return new PedidoBuilder();
    }

    public PedidoBuilder withTipo(PedidoTipo tipo) {
        this.tipo = tipo;
        return this;
    }

    public PedidoBuilder withTotal(Double total) {
        this.total = total;
        return this;
    }

    public PedidoBuilder withDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public PedidoBuilder withDataFim(Date dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public PedidoBuilder withMesa(Integer mesa) {
        this.mesa = mesa;
        return this;
    }

    public PedidoBuilder withBalcao(Integer balcao) {
        this.balcao = balcao;
        return this;
    }

    public PedidoBuilder withStatus(PedidoStatusTipo status) {
        this.status = status;
        return this;
    }

    public PedidoBuilder withAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public PedidoBuilder withUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public PedidoBuilder withCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder withItems(List<ItemPedido> items) {
        this.items = items;
        return this;
    }

    public Pedido build() {
        Pedido pedido = new Pedido();
        pedido.setTipo(tipo);
        pedido.setTotal(total);
        pedido.setDataInicio(dataInicio);
        pedido.setDataFim(dataFim);
        pedido.setMesa(mesa);
        pedido.setBalcao(balcao);
        pedido.setStatus(status);
        pedido.setAtivo(ativo);
        pedido.setUsuario(usuario);
        pedido.setCliente(cliente);
        pedido.setItems(items);
        return pedido;
    }
}

package br.uem.apoioarestaurante.models;

import br.uem.apoioarestaurante.dao.PedidoDAO;
import br.uem.apoioarestaurante.metadata.entities.ItemPedido;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.entities.Produto;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;
import br.uem.apoioarestaurante.views.PedidoView;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class PedidoModel implements Serializable {

    @Inject
    private PedidoView pedidoView;

    private PedidoDAO pedidoDAO;

    public PedidoModel() {
        pedidoDAO = PedidoDAO.getInstance();
    }

    public List<Pedido> findOrdersByFilters(PedidoTipo pedidoTipo, boolean byId, long orderId, boolean byClient, long clientId, boolean byUser, long userId, boolean byTable, int table) {
        pedidoDAO.connect();

        List<Pedido> pedidos = (!byId && !byClient && !byUser && !byTable)
                ? pedidoDAO.findByOrderType(pedidoTipo)
                : pedidoDAO.findOrdersByFilters(pedidoTipo, byId, orderId, byClient, clientId, byUser, userId, byTable, table);

        pedidoDAO.disconnect();

        return pedidos;
    }

    public List<Produto> findProductsByFilters(boolean byId, long producId, boolean byDescription, String description) {

        return pedidoDAO.findProductsByFilters(byId, producId, byDescription, description);
    }

    public Pedido save(Pedido pedido) {
        pedidoDAO.connect();

        pedidoDAO.save(pedido);

        pedidoDAO.disconnect();

        return pedido;
    }

    public Pedido update(Pedido pedido) {
        pedidoDAO.connect();

        pedidoDAO.update(pedido);

        pedidoDAO.disconnect();

        return pedido;
    }

    public ItemPedido update(ItemPedido itemPedido) {
        pedidoDAO.updateItemPedido(itemPedido);

        return itemPedido;
    }
}

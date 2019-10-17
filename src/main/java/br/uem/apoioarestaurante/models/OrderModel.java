package br.uem.apoioarestaurante.models;

import br.uem.apoioarestaurante.dao.OrderDAO;
import br.uem.apoioarestaurante.metadata.entities.Order;
import br.uem.apoioarestaurante.views.OrderView;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
public class OrderModel implements Serializable {

    @Inject
    private OrderView orderView;

    private OrderDAO orderDAO;

    public OrderModel() {
        orderDAO = OrderDAO.getInstance();
    }

    public List<Order> seachByFilters(boolean byId, long orderId, boolean byClient, long clientId, boolean byUser, long userId, boolean byTable, int table) {
        orderDAO.connect();

        List<Order> orders = orderDAO.seachByFilters(byId, orderId, byClient, clientId, byUser, userId, byTable, table);

        orderDAO.disconnect();

        return orders;
    }
}

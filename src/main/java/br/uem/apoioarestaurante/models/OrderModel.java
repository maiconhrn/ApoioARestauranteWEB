package br.uem.apoioarestaurante.models;

import br.uem.apoioarestaurante.dao.OrderDAO;
import br.uem.apoioarestaurante.metadata.entities.Order;
import br.uem.apoioarestaurante.metadata.entities.Product;
import br.uem.apoioarestaurante.metadata.types.OrderType;
import br.uem.apoioarestaurante.views.OrderView;

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
public class OrderModel implements Serializable {

    @Inject
    private OrderView orderView;

    private OrderDAO orderDAO;

    public OrderModel() {
        orderDAO = OrderDAO.getInstance();
    }

    public List<Order> findOrdersByFilters(OrderType orderType, boolean byId, long orderId, boolean byClient, long clientId, boolean byUser, long userId, boolean byTable, int table) {
        orderDAO.connect();

        List<Order> orders = (!byId && !byClient && !byUser && !byTable)
                ? orderDAO.findByOrderType(orderType)
                : orderDAO.findOrdersByFilters(orderType, byId, orderId, byClient, clientId, byUser, userId, byTable, table);

        orderDAO.disconnect();

        return orders;
    }

    public List<Product> findProductsByFilters(boolean byId, long producId, boolean byDescription, String description) {

        return orderDAO.findProductsByFilters(byId, producId, byDescription, description);
    }

    public Order save(Order order) {
        orderDAO.connect();

        orderDAO.save(order);

        orderDAO.disconnect();

        return order;
    }
}

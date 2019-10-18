package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.Order;
import br.uem.apoioarestaurante.models.OrderModel;
import br.uem.apoioarestaurante.views.OrderView;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class OrderController implements Serializable {

    @Inject
    private OrderView orderView;

    private OrderModel orderModel;

    public OrderView getOrderView() {
        return orderView;
    }

    public void setOrderView(OrderView orderView) {
        this.orderView = orderView;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public void search() {

        List<Order> res = orderModel.seachByFilters(
                orderView.getType(),
                orderView.isIdFilterSelected(),
                Long.parseLong(orderView.getIdFilter().equals("") ? "0" : orderView.getIdFilter()),
                orderView.isClientFilterSelected(),
                Long.parseLong(orderView.getClientFilter().equals("") ? "0" : orderView.getClientFilter()),
                orderView.isUserFilterSelected(),
                Long.parseLong(orderView.getUserFilter().equals("") ? "0" : orderView.getUserFilter()),
                orderView.isTableFilterSelected(),
                Integer.parseInt(orderView.getTableFilter().equals("") ? "0" : orderView.getTableFilter()));

        orderView.setOrders(res != null ? res : new ArrayList<>());
        orderView.setSelectedOrder(null);
    }

    public void newOrder() {

    }

    public void editOrder() {

    }

    public void deleteOrder() {

    }

    public void closeOrder() {

    }
}

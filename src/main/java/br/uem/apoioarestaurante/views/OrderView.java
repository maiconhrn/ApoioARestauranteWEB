package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.controllers.OrderController;
import br.uem.apoioarestaurante.metadata.entities.Order;
import br.uem.apoioarestaurante.metadata.types.OrderType;
import br.uem.apoioarestaurante.models.OrderModel;

import javax.annotation.PostConstruct;
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
public class OrderView implements Serializable {

    @Inject
    private OrderController orderController;

    private OrderModel orderModel;

    private boolean idFilterSelected;
    private boolean clientFilterSelected;
    private boolean userFilterSelected;
    private boolean tableFilterSelected;
    private OrderType type = OrderType.DELIVERY;
    private List<Order> orders = new ArrayList<>();
    private Order selectedOrder;
    private String idFilter;
    private String clientFilter;
    private String userFilter;
    private String tableFilter;

    @PostConstruct
    public void init() {
        orderModel = new OrderModel();
        orderController.setOrderModel(orderModel);

        type = OrderType.BOTH;
        orders = new ArrayList<>();
    }

    public boolean isIdFilterSelected() {
        return idFilterSelected;
    }

    public void setIdFilterSelected(boolean idFilterSelected) {
        this.idFilterSelected = idFilterSelected;
    }

    public boolean isClientFilterSelected() {
        return clientFilterSelected;
    }

    public void setClientFilterSelected(boolean clientFilterSelected) {
        this.clientFilterSelected = clientFilterSelected;
    }

    public boolean isUserFilterSelected() {
        return userFilterSelected;
    }

    public void setUserFilterSelected(boolean userFilterSelected) {
        this.userFilterSelected = userFilterSelected;
    }

    public boolean isTableFilterSelected() {
        return tableFilterSelected;
    }

    public void setTableFilterSelected(boolean tableFilterSelected) {
        this.tableFilterSelected = tableFilterSelected;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderType[] getTypes() {
        return OrderType.values();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public String getIdFilter() {
        return idFilter;
    }

    public void setIdFilter(String idFilter) {
        this.idFilter = idFilter;
    }

    public String getClientFilter() {
        return clientFilter;
    }

    public void setClientFilter(String clientFilter) {
        this.clientFilter = clientFilter;
    }

    public String getUserFilter() {
        return userFilter;
    }

    public void setUserFilter(String userFilter) {
        this.userFilter = userFilter;
    }

    public String getTableFilter() {
        return tableFilter;
    }

    public void setTableFilter(String tableFilter) {
        this.tableFilter = tableFilter;
    }

    public OrderController getOrderController() {
        return orderController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }
}

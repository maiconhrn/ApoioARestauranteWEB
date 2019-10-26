package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.Order;
import br.uem.apoioarestaurante.models.OrderModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.OrderView;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    @Inject
    private OrderModel orderModel;

    public void search() {

        List<Order> res = orderModel.findOrdersByFilters(
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

    public String newOrder() {
        return FacesUtil.ORDER_MAINTENANCE + "&type=create";
    }

    public String editOrder() {
        if (orderView.getSelectedOrder() != null) {
            return FacesUtil.ORDER_MAINTENANCE + "&type=edit&id=" + orderView.getSelectedOrder().getId();
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Pedido na tabela para essa ação."));

        return "";
    }

    public void deleteOrder() {

    }

    public void closeOrder() {

    }
}

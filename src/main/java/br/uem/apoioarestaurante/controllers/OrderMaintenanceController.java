package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.Order;
import br.uem.apoioarestaurante.metadata.entities.OrderItem;
import br.uem.apoioarestaurante.models.OrderModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.OrderMaintenanceView;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class OrderMaintenanceController implements Serializable {

    @Inject
    private OrderMaintenanceView orderMaintenanceView;

    @Inject
    private OrderModel orderModel;

    public String newOrderItem() {
        return FacesUtil.ORDER_ITEM_PRODUCT;
    }

    public void deleteOrderItem() {
        Order order = orderMaintenanceView.getOrder();
        OrderItem orderItem = orderMaintenanceView.getSelectedOrderItem();

        if (orderItem != null) {
            order.getItems().remove(orderItem);

            return;
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Item do Pedido na tabela para essa ação."));
    }

    public void save() {
        Order orderSaved = orderModel.save(orderMaintenanceView.getOrder());

        if (orderSaved != null && orderSaved.getId() != null) {
            orderMaintenanceView.setOrder(orderSaved);

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Sucesso!", "O Pedido foi salvo!"));
        }
    }

    public String cancel() {
        return FacesUtil.ORDER;
    }
}

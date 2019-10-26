package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.Order;
import br.uem.apoioarestaurante.metadata.entities.OrderItem;
import br.uem.apoioarestaurante.metadata.entities.Product;
import br.uem.apoioarestaurante.models.OrderModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.OrderItemProductView;
import br.uem.apoioarestaurante.views.OrderMaintenanceView;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@ViewScoped
public class OrderItemProductController implements Serializable {

    @Inject
    private OrderItemProductView orderItemProductView;

    @Inject
    private OrderMaintenanceView orderMaintenanceView;

    @Inject
    private OrderModel orderModel;

    public void search() {
        List<Product> res = orderModel.findProductsByFilters(orderItemProductView.isIdFilterSelected(),
                Long.parseLong(orderItemProductView.getIdFilter().equals("") ? "0" : orderItemProductView.getIdFilter()),
                orderItemProductView.isDescriptionFilterSelected(),
                orderItemProductView.getDescriptionFilter());

        orderItemProductView.setProducts(res);
    }

    public String selectProduct() {
        if (orderItemProductView.getSelectedProduct() != null) {
            Order order = orderMaintenanceView.getOrder();
            Product product = orderItemProductView.getSelectedProduct();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductQtt(1);
            orderItem.setPrice(orderItem.getProductQtt() * product.getSellPrice());
            orderItem.setProduct(product);

            order.getItems().add(orderItem);

            order.setTotalPrice(order.getTotalPrice() + orderItem.getPrice());

            return FacesUtil.ORDER_MAINTENANCE + "&editing=true";
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Produto na tabela para essa ação."));

        return "";
    }
}

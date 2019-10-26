package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.controllers.ClientController;
import br.uem.apoioarestaurante.controllers.OrderController;
import br.uem.apoioarestaurante.controllers.UserController;
import br.uem.apoioarestaurante.metadata.entities.Client;
import br.uem.apoioarestaurante.metadata.entities.Order;
import br.uem.apoioarestaurante.metadata.entities.OrderItem;
import br.uem.apoioarestaurante.metadata.entities.User;
import br.uem.apoioarestaurante.metadata.types.OrderType;
import br.uem.apoioarestaurante.models.OrderModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import org.primefaces.event.CellEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class OrderMaintenanceView implements Serializable {

    @Inject
    private OrderView orderView;

    @Inject
    private OrderController orderController;

    @Inject
    private ClientController clientController;

    @Inject
    private UserController userController;

    @Inject
    private OrderModel orderModel;

    private Date minDate;

    private List<Client> clients;
    private List<User> users;

    private Order order;
    private OrderItem selectedOrderItem;

    private void initForCreateOrder() {
        clients = clientController.findAllClients();
        users = userController.findAllUser();
        order = new Order();
        order.setType(OrderType.LOCAL);
    }

    private void initForEditOrder(String orderId) {
        //tratar para buscar os dados do pedido com o id == orderId
    }

    @PostConstruct
    public void init() {
        String maintenanceType = FacesUtil.getParam("type");
        String editing = FacesUtil.getParam("editing");
        String orderId = FacesUtil.getParam("id");

        if (editing == null || editing.equals("false")) {
            if (maintenanceType != null) {
                if (maintenanceType.equals("create")) {
                    initForCreateOrder();
                } else if (maintenanceType.equals("edit")) {
                    initForEditOrder(orderId);
                }
            }
        }
    }

    public void onProductQttEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        order.setTotalPrice(0D);
        order.getItems().forEach((i) -> {
            i.setPrice(i.getProduct().getSellPrice() * i.getProductQtt());

            order.setTotalPrice(order.getTotalPrice() + i.getPrice());
        });
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderType[] getTypes() {
        return OrderType.values();
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public OrderItem getSelectedOrderItem() {
        return selectedOrderItem;
    }

    public void setSelectedOrderItem(OrderItem selectedOrderItem) {
        this.selectedOrderItem = selectedOrderItem;
    }
}

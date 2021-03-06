package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.controllers.ClienteController;
import br.uem.apoioarestaurante.controllers.PedidoController;
import br.uem.apoioarestaurante.controllers.UsuarioController;
import br.uem.apoioarestaurante.metadata.entities.Cliente;
import br.uem.apoioarestaurante.metadata.entities.ItemPedido;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.entities.Usuario;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;
import br.uem.apoioarestaurante.models.PedidoModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import org.primefaces.event.CellEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class ManutencaoPedidoView implements Serializable {

    @Inject
    private PedidoView pedidoView;

    @Inject
    private PedidoController pedidoController;

    @Inject
    private ClienteController clienteController;

    @Inject
    private UsuarioController usuarioController;

    @Inject
    private PedidoModel pedidoModel;

    private Date minDate;

    private List<Cliente> clientes;
    private List<Usuario> usuarios;
    private List<ItemPedido> itemPedidosRemoved;
    private List<ItemPedido> itemsNewOrUpdated;

    private Pedido pedido;
    private ItemPedido selectedItemPedido;

    private void initForCreateOrder() {
        clientes = clienteController.findAllClients();
        usuarios = usuarioController.findAllUser();
        pedido = new Pedido();
        pedido.setTipo(PedidoTipo.LOCAL);
        itemPedidosRemoved = new ArrayList<>();
        itemsNewOrUpdated = new ArrayList<>();
    }

    private void initForEditOrder(String id) {
        clientes = clienteController.findAllClients();
        usuarios = usuarioController.findAllUser();

        pedido = pedidoController.findById(Long.parseLong(id));
        itemPedidosRemoved = new ArrayList<>();
        itemsNewOrUpdated = new ArrayList<>();
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
        ItemPedido itemPedido = pedido.getItems().stream().filter(i -> i.getProduto().getId() == Long.parseLong(event.getRowKey())).findFirst().orElse(null);

        if (itemPedido != null && !itemsNewOrUpdated.contains(itemPedido)) {
            itemsNewOrUpdated.add(itemPedido);
        }

        pedido.refreshTotal();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoTipo[] getTypes() {
        List<PedidoTipo> tipos = new ArrayList<>(Arrays.asList(PedidoTipo.values()));

        tipos.remove(PedidoTipo.BOTH);

        return tipos.toArray(new PedidoTipo[0]);
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public ItemPedido getSelectedItemPedido() {
        return selectedItemPedido;
    }

    public void setSelectedItemPedido(ItemPedido selectedItemPedido) {
        this.selectedItemPedido = selectedItemPedido;
    }

    public List<ItemPedido> getItemPedidosRemoved() {
        return itemPedidosRemoved;
    }

    public void setItemPedidosRemoved(List<ItemPedido> itemPedidosRemoved) {
        this.itemPedidosRemoved = itemPedidosRemoved;
    }

    public List<ItemPedido> getItemsNewOrUpdated() {
        return itemsNewOrUpdated;
    }

    public void setItemsNewOrUpdated(List<ItemPedido> itemsNewOrUpdated) {
        this.itemsNewOrUpdated = itemsNewOrUpdated;
    }
}

package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.controllers.PedidoController;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;
import br.uem.apoioarestaurante.models.PedidoModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class PedidoView implements Serializable {

    @Inject
    private PedidoController pedidoController;

    @Inject
    private PedidoModel pedidoModel;

    private boolean idFilterSelected;
    private boolean clientFilterSelected;
    private boolean userFilterSelected;
    private boolean tableFilterSelected;
    private PedidoTipo type;
    private List<Pedido> pedidos = new ArrayList<>();
    private Pedido selectedPedido;
    private String idFilter;
    private String clientFilter;
    private String userFilter;
    private String tableFilter;

    @PostConstruct
    public void init() {
        type = PedidoTipo.BOTH;
        pedidos = new ArrayList<>();
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

    public PedidoTipo getType() {
        return type;
    }

    public void setType(PedidoTipo type) {
        this.type = type;
    }

    public PedidoTipo[] getTypes() {
        return PedidoTipo.values();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getSelectedPedido() {
        return selectedPedido;
    }

    public void setSelectedPedido(Pedido selectedPedido) {
        this.selectedPedido = selectedPedido;
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
}

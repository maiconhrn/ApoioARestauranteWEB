package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.types.PedidoStatusTipo;
import br.uem.apoioarestaurante.models.PedidoModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.PedidoView;

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
public class PedidoController implements Serializable {

    @Inject
    private PedidoView pedidoView;

    @Inject
    private PedidoModel pedidoModel;

    public void search() {

        List<Pedido> res = pedidoModel.findOrdersByFilters(
                pedidoView.getType(),
                pedidoView.isIdFilterSelected(),
                Long.parseLong(pedidoView.getIdFilter().equals("") ? "0" : pedidoView.getIdFilter()),
                pedidoView.isClientFilterSelected(),
                Long.parseLong(pedidoView.getClientFilter().equals("") ? "0" : pedidoView.getClientFilter()),
                pedidoView.isUserFilterSelected(),
                Long.parseLong(pedidoView.getUserFilter().equals("") ? "0" : pedidoView.getUserFilter()),
                pedidoView.isTableFilterSelected(),
                Integer.parseInt(pedidoView.getTableFilter().equals("") ? "0" : pedidoView.getTableFilter()));

        pedidoView.setPedidos(res != null ? res : new ArrayList<>());
        pedidoView.setSelectedPedido(null);
    }

    public String newOrder() {
        return FacesUtil.ORDER_MAINTENANCE + "&type=create";
    }

    public String editOrder() {
        if (pedidoView.getSelectedPedido() != null) {
            return FacesUtil.ORDER_MAINTENANCE + "&type=edit&id=" + pedidoView.getSelectedPedido().getId();
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Pedido na tabela para essa ação."));

        return "";
    }

    public void deleteOrder() {
        Pedido pedidoFromView = pedidoView.getSelectedPedido();

        if (pedidoFromView != null) {
            boolean res = pedidoModel.delete(pedidoFromView);

            if (res) {
                pedidoView.getPedidos().remove(pedidoFromView);

                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Sucesso!", "O Pedido foi removido!"));
            }

            return;
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Pedido na tabela para essa ação."));
    }
    
    public String closeOrder() {
        
        if (pedidoView.getSelectedPedido() != null && pedidoView.getSelectedPedido().getStatus()==PedidoStatusTipo.OPENED) {
            
            return FacesUtil.FECHAR_PEDIDO + "&type=edit&id=" + pedidoView.getSelectedPedido().getId();
        }
        else if (pedidoView.getSelectedPedido().getStatus() == PedidoStatusTipo.CLOSED) {
            FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "O pedido selecionado já se encontra fechado!"));
            return "";

        }
        else{
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Pedido na tabela para essa ação."));
        }
        return "";
    }

    public Pedido findById(Long id) {
        return pedidoModel.findById(id);
    }
}

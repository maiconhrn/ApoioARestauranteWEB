package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.ItemPedido;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.models.PedidoModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.FecharPedidoView;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Universidade Estadual de Maringá
 *
 * Autor: José Gabriel Júnior       Ra: 54011
 */

@Named
@SessionScoped
public class FecharPedidoController implements Serializable {

    @Inject
    private FecharPedidoView fecharPedidoView;

    @Inject
    private PedidoModel pedidoModel;


    public void deleteOrderItem() {
        Pedido pedido = fecharPedidoView.getPedido();
        ItemPedido itemPedido = fecharPedidoView.getSelectedItemPedido();

        if (itemPedido != null) {
            itemPedido.setAtivo(false);
            pedido.getItems().remove(itemPedido);
            pedido.refreshTotal();
            fecharPedidoView.getItemPedidosRemoved().add(itemPedido);

            return;
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Item do Pedido na tabela para essa ação."));
    }

    private void showSaveSuccess(Pedido pedidoSaved) {
        if (pedidoSaved.getId() != null) {
            fecharPedidoView.setPedido(pedidoSaved);

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Sucesso!", "O Pedido foi encerrado!"));
        }
    }
    
    public String closeOrder() {
        
        if (fecharPedidoView.getSelectedPedido() != null) {
            return FacesUtil.FECHAR_PEDIDO + "&type=edit&id=" + pedidoView.getSelectedPedido().getId();
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Pedido na tabela para essa ação."));

        return "";
    }

    public String cancel() {
        return FacesUtil.ORDER;
    }
}

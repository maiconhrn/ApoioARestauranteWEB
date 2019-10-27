package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.ItemPedido;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.models.PedidoModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.ManutencaoPedidoView;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class ManutencaoPedidoController implements Serializable {

    @Inject
    private ManutencaoPedidoView manutencaoPedidoView;

    @Inject
    private PedidoModel pedidoModel;

    public String newOrderItem() {
        return FacesUtil.ORDER_ITEM_PRODUCT;
    }

    public void deleteOrderItem() {
        Pedido pedido = manutencaoPedidoView.getPedido();
        ItemPedido itemPedido = manutencaoPedidoView.getSelectedItemPedido();

        if (itemPedido != null) {
            itemPedido.setAtivo(false);
            pedido.getItems().remove(itemPedido);
            pedido.refreshTotal();
            manutencaoPedidoView.getItemPedidosRemoved().add(itemPedido);

            return;
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Item do Pedido na tabela para essa ação."));
    }

    private void showSaveSuccess(Pedido pedidoSaved) {
        if (pedidoSaved.getId() != null) {
            manutencaoPedidoView.setPedido(pedidoSaved);

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Sucesso!", "O Pedido foi salvo!"));
        }
    }

    public void save() {
        Pedido pedidoView = manutencaoPedidoView.getPedido();
        List<ItemPedido> itensPedido = manutencaoPedidoView.getItemPedidosRemoved();
        itensPedido.addAll(manutencaoPedidoView.getItemsNewOrUpdated());

        Pedido pedidoSaved;
        if (pedidoView != null) {
            pedidoSaved = (pedidoView.getId() != null && pedidoView.getId() != 0D)
                    ? pedidoModel.update(pedidoView)
                    : pedidoModel.save(pedidoView);

            itensPedido.forEach(i -> {
                if (i != null && i.getId() != null) {
                    try {
                        pedidoModel.update(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            itensPedido.clear();

            showSaveSuccess(pedidoSaved);
        }
    }

    public String cancel() {
        return FacesUtil.ORDER;
    }
}

package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.ItemPedido;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.entities.Produto;
import br.uem.apoioarestaurante.models.PedidoModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.ManutencaoPedidoView;
import br.uem.apoioarestaurante.views.ProdutoItemPedidoView;

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
public class ProdutoItemPedidoController implements Serializable {

    @Inject
    private ProdutoItemPedidoView produtoItemPedidoView;

    @Inject
    private ManutencaoPedidoView manutencaoPedidoView;

    @Inject
    private PedidoModel pedidoModel;

    public void search() {
        List<Produto> res = pedidoModel.findProductsByFilters(produtoItemPedidoView.isIdFilterSelected(),
                Long.parseLong(produtoItemPedidoView.getIdFilter().equals("") ? "0" : produtoItemPedidoView.getIdFilter()),
                produtoItemPedidoView.isDescriptionFilterSelected(),
                produtoItemPedidoView.getDescriptionFilter());

        produtoItemPedidoView.setProdutos(res);
    }

    public String selectProduct() {
        if (produtoItemPedidoView.getSelectedProduto() != null) {
            Pedido pedido = manutencaoPedidoView.getPedido();
            Produto produto = produtoItemPedidoView.getSelectedProduto();
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setQtdProduto(1);
            itemPedido.setPreco(itemPedido.getQtdProduto() * produto.getPrecoVenda());
            itemPedido.setProduto(produto);

            pedido.getItems().add(itemPedido);

            pedido.setTotal(pedido.getTotal() + itemPedido.getPreco());

            manutencaoPedidoView.getItemsNewOrUpdated().add(itemPedido);
            manutencaoPedidoView.getItemPedidosRemoved().remove(itemPedido);
            return FacesUtil.ORDER_MAINTENANCE + "&editing=true";
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Produto na tabela para essa ação."));

        return "";
    }
}

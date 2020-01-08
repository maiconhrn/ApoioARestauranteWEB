package br.uem.apoioarestaurante.models;

import br.uem.apoioarestaurante.dao.ItemPedidoDAO;
import br.uem.apoioarestaurante.dao.PedidoDAO;
import br.uem.apoioarestaurante.exceptions.EstoqueException;
import br.uem.apoioarestaurante.metadata.entities.*;
import br.uem.apoioarestaurante.metadata.types.MovimentoEstoqueTipo;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;
import br.uem.apoioarestaurante.views.PedidoView;
import org.hibernate.annotations.Where;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class PedidoModel implements Serializable {

    @Inject
    private PedidoView pedidoView;

    private PedidoDAO pedidoDAO;

    public PedidoModel() {
        pedidoDAO = PedidoDAO.getInstance();
    }

    @Where(clause = "ativo = true")
    public List<Pedido> findOrdersByFilters(PedidoTipo pedidoTipo, boolean byId, long orderId, boolean byClient, long clientId, boolean byUser, long userId, boolean byTable, int table) {
        pedidoDAO.connect();

        List<Pedido> pedidos = (!byId && !byClient && !byUser && !byTable)
                ? pedidoDAO.findByOrderType(pedidoTipo)
                : pedidoDAO.findOrdersByFilters(pedidoTipo, byId, orderId, byClient, clientId, byUser, userId, byTable, table);

        pedidoDAO.disconnect();

        return pedidos;
    }

    @Where(clause = "ativo = true")
    public List<Produto> findProductsByFilters(boolean byId, long producId, boolean byDescription, String description) {

        return pedidoDAO.findProductsByFilters(byId, producId, byDescription, description);
    }

    public Pedido save(Pedido pedido) {
        pedidoDAO.connect();

        pedidoDAO.save(pedido);

        pedidoDAO.disconnect();

        return pedido;
    }

    public Pedido update(Pedido pedido) {
        pedidoDAO.connect();

        pedidoDAO.update(pedido);

        pedidoDAO.disconnect();

        return pedido;
    }

    public Pedido saveOrUpdate(Pedido pedido) {
        return (pedido.getId() != null && pedido.getId() != 0D)
                ? update(pedido)
                : save(pedido);
    }

    public ItemPedido update(ItemPedido itemPedido) throws Exception {
        Estoque estoque = itemPedido.getProduto().getEstoque();

        MovimentoEstoque movimentoEstoque = null;
        try {
            if (estoque != null) {
                if (!itemPedido.getAtivo()) {
                    movimentoEstoque = estoque.novaMovimentacaoEstoque(
                            MovimentoEstoqueTipo.IN,
                            itemPedido.getQtdProduto(),
                            itemPedido.getPedido().getUsuario());

                    pedidoDAO.saveMovimentoEstoque(movimentoEstoque);
                } else {
                    if (itemPedido.getId() != null) {
                        ItemPedidoDAO itemPedidoDAO = ItemPedidoDAO.getInstance();

                        itemPedidoDAO.connect();
                        ItemPedido antigo = itemPedidoDAO.findById(itemPedido.getId());
                        itemPedidoDAO.disconnect();

                        if (antigo != null) {
                            int diff = itemPedido.getQtdProduto() - antigo.getQtdProduto();

                            if (diff != 0) {
                                movimentoEstoque = estoque.novaMovimentacaoEstoque(
                                        diff < 0 ? MovimentoEstoqueTipo.IN : MovimentoEstoqueTipo.OUT,
                                        Math.abs(diff),
                                        itemPedido.getPedido().getUsuario());

                                pedidoDAO.saveMovimentoEstoque(movimentoEstoque);
                            }
                        }
                    } else {
                        movimentoEstoque = estoque.novaMovimentacaoEstoque(
                                MovimentoEstoqueTipo.OUT,
                                itemPedido.getQtdProduto(),
                                itemPedido.getPedido().getUsuario());

                        pedidoDAO.saveMovimentoEstoque(movimentoEstoque);
                    }
                }
            }

            pedidoDAO.updateItemPedido(itemPedido);
        } catch (EstoqueException e) {
            e.printStackTrace();

            throw e;
        }

        return itemPedido;
    }

    public boolean delete(Pedido pedido) {
        pedidoDAO.connect();

        pedido.getItems().forEach(i -> i.setAtivo(false));
        pedido.setAtivo(false);

        Pedido res = pedidoDAO.update(pedido);

        pedidoDAO.disconnect();

        return res != null;
    }

    public Pedido findById(Long id) {
        if (id == null) {
            return null;
        }

        pedidoDAO.connect();

        Pedido res = pedidoDAO.findById(id);

        pedidoDAO.disconnect();

        return res;
    }
}

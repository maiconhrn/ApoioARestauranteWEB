package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.metadata.entities.ItemPedido;

/**
 * @author Maicon
 */
public class ItemPedidoDAO extends HibernateBasicGenericDAO<ItemPedido> {

    private static ItemPedidoDAO ourInstance = new ItemPedidoDAO();

    public static ItemPedidoDAO getInstance() {
        return ourInstance;
    }

    private ItemPedidoDAO() {
        super(ItemPedido.class);
    }
}

package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.ItemPedido;

/**
 * @author Maicon
 */
public class ItemPedidoDAO extends HibernateBasicGenericDAO<ItemPedido> {

    private static ItemPedidoDAO ourInstance = new ItemPedidoDAO();

    public static ItemPedidoDAO getInstance() {
        return ourInstance;
    }

    public ItemPedido findById(Long id) {
        try {
            requireOpenSession();

            return getSession().get(ItemPedido.class, id);
        } catch (DAOException e) {
            e.printStackTrace();

            return null;
        }
    }

    public ItemPedidoDAO() {
        super(ItemPedido.class);
    }
}

package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.*;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maicon
 */
public class PedidoDAO extends HibernateBasicGenericDAO<Pedido> {

    private static PedidoDAO ourInstance = new PedidoDAO();

    public static PedidoDAO getInstance() {
        return ourInstance;
    }

    private ProdutoDAO produtoDAO;
    private ItemPedidoDAO itemPedidoDAO;

    private PedidoDAO() {
        super(Pedido.class);

        produtoDAO = ProdutoDAO.getInstance();
        itemPedidoDAO = ItemPedidoDAO.getInstance();
    }

    public List<Pedido> findByOrderType(PedidoTipo pedidoTipo) {
        try {
            requireOpenSession();

            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Pedido> criteriaQuery = cb.createQuery(Pedido.class);
            Root<Pedido> root = criteriaQuery.from(Pedido.class);

            List<Predicate> predicates = new ArrayList<>();

            if (pedidoTipo != PedidoTipo.BOTH) {
                predicates.add(cb.equal(root.get("tipo"), pedidoTipo));
            }

            return getSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0]))).getResultList();
        } catch (DAOException e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public List<Pedido> findOrdersByFilters(PedidoTipo pedidoTipo, boolean byId, long orderId, boolean byClient, long clientId, boolean byUser, long userId, boolean byTable, int table) {
        try {
            requireOpenSession();

            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Pedido> criteriaQuery = cb.createQuery(Pedido.class);
            Root<Pedido> root = criteriaQuery.from(Pedido.class);

            List<Predicate> predicates = new ArrayList<>();

            if (pedidoTipo != PedidoTipo.BOTH) {
                predicates.add(cb.equal(root.get("tipo"), pedidoTipo));
            }

            if (byId) {
                predicates.add(cb.equal(root.get("id"), orderId));
            }

            if (byClient) {
                Join<Pedido, Cliente> clientJoin = root.join("cliente");
                predicates.add(cb.equal(clientJoin.get("id"), clientId));
            }

            if (byUser) {
                Join<Pedido, Usuario> clientJoin = root.join("usuario");
                predicates.add(cb.equal(clientJoin.get("id"), userId));
            }

            if (byTable) {
                predicates.add(cb.equal(root.get("mesa"), table));
            }

            return getSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0]))).getResultList();
        } catch (DAOException e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public List<Produto> findProductsByFilters(boolean byId, long producId, boolean byDescription, String description) {
        produtoDAO.connect();

        List<Produto> produtos = produtoDAO.findProductsByFilters(byId, producId, byDescription, description);

        produtoDAO.disconnect();

        return produtos != null ? produtos : new ArrayList<>();
    }

    public ItemPedido updateItemPedido(ItemPedido itemPedido) {
        itemPedidoDAO.connect();

        ItemPedido res = itemPedidoDAO.update(itemPedido);

        itemPedidoDAO.disconnect();

        return res;
    }
}

package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.*;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    private EstoqueDAO estoqueDAO;

    private PedidoDAO() {
        super(Pedido.class);

        produtoDAO = ProdutoDAO.getInstance();
        itemPedidoDAO = ItemPedidoDAO.getInstance();
        estoqueDAO = EstoqueDAO.getInstance();
    }

    public List<Pedido> findInInitialDateRange(Date de, Date ate) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Pedido> criteriaQuery = cb.createQuery(Pedido.class);
            Root<Pedido> root = criteriaQuery.from(Pedido.class);
            List<Predicate> predicates = new ArrayList<>(Arrays.asList(cb.greaterThanOrEqualTo(root.get("dataInicio"), de),
                    cb.lessThanOrEqualTo(root.get("dataInicio"), ate)));

            return getSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0]))).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Pedido> findByOrderType(PedidoTipo pedidoTipo) {
        try {
            requireOpenSession();

            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Pedido> criteriaQuery = cb.createQuery(Pedido.class);
            Root<Pedido> root = criteriaQuery.from(Pedido.class);

            List<Predicate> predicates = new ArrayList<>(Arrays.asList(cb.equal(root.get("ativo"), true)));

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

            List<Predicate> predicates = new ArrayList<>(Arrays.asList(cb.equal(root.get("ativo"), true)));

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

    public MovimentoEstoque saveMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
        estoqueDAO.connect();

        MovimentoEstoque res = estoqueDAO.saveMovimentoEstoque(movimentoEstoque);

        estoqueDAO.update(movimentoEstoque.getEstoque());

        estoqueDAO.disconnect();

        return res;
    }

    public Pedido findById(Long id) {
        try {
            requireOpenSession();

            return getSession().get(Pedido.class, id);
        } catch (DAOException e) {
            e.printStackTrace();

            return null;
        }
    }
}

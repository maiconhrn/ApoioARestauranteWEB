package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.Client;
import br.uem.apoioarestaurante.metadata.entities.Order;
import br.uem.apoioarestaurante.metadata.entities.User;
import br.uem.apoioarestaurante.metadata.types.OrderType;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maicon
 */
public class OrderDAO extends HibernateBasicGenericDAO<Order> {

    private static OrderDAO ourInstance = new OrderDAO();

    public static OrderDAO getInstance() {
        return ourInstance;
    }

    private OrderDAO() {
        super(Order.class);
    }

    public List<Order> findByOrderType(OrderType orderType) {
        try {
            requireOpenSession();

            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = cb.createQuery(Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);

            List<Predicate> predicates = new ArrayList<>();

            if (orderType != OrderType.BOTH) {
                predicates.add(cb.equal(root.get("type"), orderType));
            }

            return getSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0]))).getResultList();
        } catch (DAOException e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public List<Order> seachByFilters(OrderType orderType, boolean byId, long orderId, boolean byClient, long clientId, boolean byUser, long userId, boolean byTable, int table) {
        try {
            requireOpenSession();

            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = cb.createQuery(Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);

            List<Predicate> predicates = new ArrayList<>();

            if (orderType != OrderType.BOTH) {
                predicates.add(cb.equal(root.get("type"), orderType));
            }

            if (byId) {
                predicates.add(cb.equal(root.get("id"), orderId));
            }

            if (byClient) {
                Join<Order, Client> clientJoin = root.join("client");
                predicates.add(cb.equal(clientJoin.get("id"), clientId));
            }

            if (byUser) {
                Join<Order, User> clientJoin = root.join("user");
                predicates.add(cb.equal(clientJoin.get("id"), userId));
            }

            if (byTable) {
                predicates.add(cb.equal(root.get("table"), table));
            }

            return getSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0]))).getResultList();
        } catch (DAOException e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }
}

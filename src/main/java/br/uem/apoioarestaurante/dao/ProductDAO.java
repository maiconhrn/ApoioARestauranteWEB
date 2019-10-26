package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maicon
 */
public class ProductDAO extends HibernateBasicGenericDAO<Product> {

    private static ProductDAO ourInstance = new ProductDAO();

    public static ProductDAO getInstance() {
        return ourInstance;
    }

    private ProductDAO() {
        super(Product.class);
    }

    public List<Product> findProductsByFilters(boolean byId, long producId, boolean byDescription, String description) {
        try {
            requireOpenSession();

            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);

            List<Predicate> predicates = new ArrayList<>();

            if (byId) {
                predicates.add(cb.equal(root.get("id"), producId));
            }

            if (byDescription) {
                predicates.add(cb.like(root.get("description"), description));
            }

            return getSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0]))).getResultList();
        } catch (DAOException e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }
}

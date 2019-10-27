package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.Produto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Maicon
 */
public class ProdutoDAO extends HibernateBasicGenericDAO<Produto> {

    private static ProdutoDAO ourInstance = new ProdutoDAO();

    public static ProdutoDAO getInstance() {
        return ourInstance;
    }

    private ProdutoDAO() {
        super(Produto.class);
    }

    public List<Produto> findProductsByFilters(boolean byId, long producId, boolean byDescription, String description) {
        try {
            requireOpenSession();

            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Produto> criteriaQuery = cb.createQuery(Produto.class);
            Root<Produto> root = criteriaQuery.from(Produto.class);

            List<Predicate> predicates = new ArrayList<>(Arrays.asList(cb.equal(root.get("ativo"), true)));

            if (byId) {
                predicates.add(cb.equal(root.get("id"), producId));
            }

            if (byDescription) {
                predicates.add(cb.like(root.get("descricao"), description));
            }

            return getSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0]))).getResultList();
        } catch (DAOException e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }
}

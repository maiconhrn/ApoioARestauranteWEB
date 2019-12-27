package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.metadata.entities.MovimentoEstoque;
import br.uem.apoioarestaurante.metadata.entities.Pedido;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public class MovimentoEstoqueDAO extends HibernateBasicGenericDAO<MovimentoEstoque> {

    private static MovimentoEstoqueDAO ourInstance = new MovimentoEstoqueDAO();

    public static MovimentoEstoqueDAO getInstance() {
        return ourInstance;
    }

    private MovimentoEstoqueDAO() {
        super(MovimentoEstoque.class);
    }

    public List<MovimentoEstoque> findInInitialDateRange(Date de, Date ate) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<MovimentoEstoque> criteriaQuery = cb.createQuery(MovimentoEstoque.class);
            Root<MovimentoEstoque> root = criteriaQuery.from(MovimentoEstoque.class);
            List<Predicate> predicates = new ArrayList<>(Arrays.asList(cb.greaterThanOrEqualTo(root.get("data"), de), 
                    cb.lessThanOrEqualTo(root.get("data"), ate)));

            return getSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0]))).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}

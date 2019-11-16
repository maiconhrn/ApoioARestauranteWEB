package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.metadata.entities.MovimentoEstoque;

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
}

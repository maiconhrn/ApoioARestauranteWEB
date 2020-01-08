package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.metadata.entities.Estoque;
import br.uem.apoioarestaurante.metadata.entities.MovimentoEstoque;

/**
 * @author Maicon
 */
public class EstoqueDAO extends HibernateBasicGenericDAO<Estoque> {

    private static EstoqueDAO ourInstance = new EstoqueDAO();

    public static EstoqueDAO getInstance() {
        return ourInstance;
    }

    private MovimentoEstoqueDAO movimentoEstoqueDAO;

    public EstoqueDAO() {
        super(Estoque.class);

        movimentoEstoqueDAO = MovimentoEstoqueDAO.getInstance();
    }

    public MovimentoEstoque saveMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
        movimentoEstoqueDAO.connect();

        MovimentoEstoque res = movimentoEstoqueDAO.save(movimentoEstoque);

        movimentoEstoqueDAO.disconnect();

        return res;
    }
}


package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.MovimentoEstoqueDAO;
import br.uem.apoioarestaurante.metadata.entities.MovimentoEstoque;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


/**
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@Named
@SessionScoped
public class MovimentacaoController implements Serializable {
    private MovimentoEstoque    movimento    = new MovimentoEstoque();
    private MovimentoEstoqueDAO movimentoDao = new MovimentoEstoqueDAO();
    
    public void salvarMovimentacao( MovimentoEstoque movimento ){
        movimentoDao.connect();        
        movimentoDao.save(movimento);
        movimentoDao.disconnect();
    }

    public MovimentoEstoque getMovimento() {
        return movimento;
    }

    public void setMovimento(MovimentoEstoque movimento) {
        this.movimento = movimento;
    }

    public MovimentoEstoqueDAO getMovimentoDao() {
        return movimentoDao;
    }

    public void setMovimentoDao(MovimentoEstoqueDAO movimentoDao) {
        this.movimentoDao = movimentoDao;
    }
    
    
    
}

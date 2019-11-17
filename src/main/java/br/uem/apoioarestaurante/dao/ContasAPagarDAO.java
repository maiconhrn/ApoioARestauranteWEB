package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.models.ContasAPagar;
/**
 *
 * @author GabrielConejo
 */
public class ContasAPagarDAO extends HibernateBasicGenericDAO<ContasAPagar> {
    private static ContasAPagarDAO ourInstance = new ContasAPagarDAO();
    
    public static  ContasAPagarDAO getInstance() { return ourInstance;}
    
    public ContasAPagarDAO(){
        super(ContasAPagar.class);
    }
    
}

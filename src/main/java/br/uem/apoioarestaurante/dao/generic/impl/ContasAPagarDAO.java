package br.uem.apoioarestaurante.dao.generic.impl;

import br.uem.apoioarestaurante.models.ContasAPagar;
/**
 *
 * @author GabrielConejo
 */
public class ContasAPagarDAO extends HibernateBasicGenericDAO <ContasAPagar>{  
    private static ContasAPagarDAO ourInstance = new ContasAPagarDAO();
    
    public static  ContasAPagarDAO getInstance() { return ourInstance;}
    
    public ContasAPagarDAO(){
        super(ContasAPagar.class);
    }
    
}

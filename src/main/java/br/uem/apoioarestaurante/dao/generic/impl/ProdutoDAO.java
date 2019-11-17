package br.uem.apoioarestaurante.dao.generic.impl;


import br.uem.apoioarestaurante.models.ProdutoModel;


public class ProdutoDAO extends HibernateBasicGenericDAO<ProdutoModel>{

    
    private static ProdutoDAO ourInstance = new ProdutoDAO();
    
    public static  ProdutoDAO getInstance() { return ourInstance;}
    
    public ProdutoDAO(){
        super(ProdutoModel.class);
    }

}
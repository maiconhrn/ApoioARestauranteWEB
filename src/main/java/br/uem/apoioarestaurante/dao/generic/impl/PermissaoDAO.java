package br.uem.apoioarestaurante.dao.generic.impl;


import br.uem.apoioarestaurante.models.Permissao;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */


public class PermissaoDAO extends HibernateBasicGenericDAO<Permissao>{

    
    private static PermissaoDAO ourInstance = new PermissaoDAO();
    
    public static  PermissaoDAO getInstance() { return ourInstance;}
    
    public PermissaoDAO(){
        super(Permissao.class);
    }
}
package br.uem.apoioarestaurante.dao.generic.impl;


import br.uem.apoioarestaurante.models.Cliente;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */


public class ClienteDAO extends HibernateBasicGenericDAO<Cliente>{

    
    private static ClienteDAO ourInstance = new ClienteDAO();
    
    public static  ClienteDAO getInstance() { return ourInstance;}
    
    public ClienteDAO(){
        super(Cliente.class);
    }
}
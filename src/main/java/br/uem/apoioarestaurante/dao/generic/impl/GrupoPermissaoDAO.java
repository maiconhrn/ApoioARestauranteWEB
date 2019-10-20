package br.uem.apoioarestaurante.dao.generic.impl;


import br.uem.apoioarestaurante.models.Grupopermissao;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */


public class GrupoPermissaoDAO extends HibernateBasicGenericDAO<Grupopermissao>{

    
    private static GrupoPermissaoDAO ourInstance = new GrupoPermissaoDAO();
    
    public static  GrupoPermissaoDAO getInstance() { return ourInstance;}
    
    public GrupoPermissaoDAO(){
        super(Grupopermissao.class);
    }
}
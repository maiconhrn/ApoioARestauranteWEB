package br.uem.apoioarestaurante.dao;


import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.models.GrupoPermissao;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */


public class GrupoPermissaoDAO extends HibernateBasicGenericDAO<GrupoPermissao> {

    
    private static GrupoPermissaoDAO ourInstance = new GrupoPermissaoDAO();
    
    public static  GrupoPermissaoDAO getInstance() { return ourInstance;}
    
    public GrupoPermissaoDAO(){
        super(GrupoPermissao.class);
    }
}
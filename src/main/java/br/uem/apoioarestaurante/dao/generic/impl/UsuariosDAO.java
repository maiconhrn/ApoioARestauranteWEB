package br.uem.apoioarestaurante.dao.generic.impl;

import br.uem.apoioarestaurante.models.Usuarios;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor:           José Gabriel Júnior       Ra: 54011
 */

public class UsuariosDAO extends HibernateBasicGenericDAO<Usuarios>{

    private static UsuariosDAO ourInstance = new UsuariosDAO();
    
    public static  UsuariosDAO getInstance() { return ourInstance;}
    
    public UsuariosDAO(){
        super(Usuarios.class);
    }
    
}

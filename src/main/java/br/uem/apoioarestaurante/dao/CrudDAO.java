/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.dao;

import java.util.List;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */
public interface CrudDAO <E> { // E de entidade
    
    public void salvar(E entidade) ;
    public void deletar(E entidade) ;  
    public List<E> buscar(); 
    
    
}

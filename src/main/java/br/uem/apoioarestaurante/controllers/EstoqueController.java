/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.Produto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */
public class EstoqueController {
    
    private List<Produto> produtos = new ArrayList<>();
    
    
    public List<Produto> retornarListaProdutosFalta(){
        
        return produtos;
    }    
    
    
}

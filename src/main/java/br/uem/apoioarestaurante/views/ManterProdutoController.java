/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.models.ProdutoModel;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@ManagedBean
@SessionScoped
public class ManterProdutoController {
    
    private ProdutoModel produto;
    private ProdutoModel produtoSelecionado;
    private List<ProdutoModel> produtos = new ArrayList<>();
    private String console;

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }
    

    public ProdutoModel getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(ProdutoModel produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
    
    

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }
    
    
    
}

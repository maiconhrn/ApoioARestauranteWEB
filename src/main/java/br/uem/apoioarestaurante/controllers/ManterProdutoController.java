/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.generic.impl.ProdutoDAO;
import br.uem.apoioarestaurante.models.ProdutoModel;
import java.sql.SQLException;
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
public class ManterProdutoController{
    
    
    private ProdutoModel produtoSelecionado;
    private String consoleTipo;
    
    private ProdutoModel produto = new ProdutoModel();
    private List<ProdutoModel> produtos = new ArrayList<>();
    
    ProdutoDAO prodao;
    
    public ManterProdutoController() {
        produto = new ProdutoModel();
        prodao = new ProdutoDAO();
        produtos = null;
        prodao.connect();
        produtos = prodao.listAll();
        prodao.disconnect();
        
    }
    
    public void inserirProduto(ProdutoModel produ) throws SQLException, ClassNotFoundException {
        
        prodao.connect();
        prodao.save(produ);
        prodao.disconnect();
        produto = new ProdutoModel();
        
    }
    
    public void alterarProduto(ProdutoModel produ) throws ClassNotFoundException, SQLException {
        prodao.connect();
        prodao.update(produ);
        prodao.disconnect();
        produto = new ProdutoModel();
    }
    
    public void excluirProduto(ProdutoModel produ) throws SQLException {
        prodao.connect();
        prodao.delete(produ);
        prodao.disconnect();
        produto = new ProdutoModel();
    }
    
    public void listarProdutos() throws SQLException {
        produtos = null;
        prodao.connect();
        produtos = prodao.listAll();
        prodao.disconnect();
    }
    
    public void buscarProduto(String desc) throws SQLException {
        produtos = null;
        ProdutoModel produ = new ProdutoModel();
        List<ProdutoModel> resultado = new ArrayList<>();
        prodao.connect();
        produtos = prodao.listAll();
        for (int i = 0; i < produtos.size(); i++) {
            produ = produtos.get(i);
            if (produ.getDescricao().startsWith(desc)) {
                resultado.add(produ);
            }
        }
        produtos.clear();
        produtos = resultado;
        prodao.disconnect();
        produto = new ProdutoModel();
    }
    
    public void limparCampos(){
        produto = new ProdutoModel();
    }
    

    public ProdutoModel getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(ProdutoModel produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public String getConsoleTipo() {
        return consoleTipo;
    }

    public void setConsoleTipo(String consoleTipo) {
        this.consoleTipo = consoleTipo;
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

    public ProdutoDAO getProdao() {
        return prodao;
    }

    public void setProdao(ProdutoDAO prodao) {
        this.prodao = prodao;
    }
       
}

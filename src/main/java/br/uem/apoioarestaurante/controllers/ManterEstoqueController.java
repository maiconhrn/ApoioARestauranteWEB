/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.EstoqueDAO;
import br.uem.apoioarestaurante.metadata.entities.Estoque;
import br.uem.apoioarestaurante.metadata.entities.Produto;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@Named
@SessionScoped
public class ManterEstoqueController implements Serializable {
    
    private Estoque       estoqueSelecionado = new Estoque();
    private Estoque       estoque            = new Estoque();
    private List<Estoque> estoques           = new ArrayList<>();
    private EstoqueDAO    estoqueDao         = new EstoqueDAO();
    private final Date    data               = new Date(System.currentTimeMillis());
    private String        descricaoPesquisa; 
    private Long          idProduto;
    private double        quantidade       ;
    private String        tipoMovimentacao ;
    
    
    
    public ManterEstoqueController() throws SQLException {  
        this.estoqueSelecionado.setProduto( new Produto() );
        this.listarEstoque();
    }
    
    public void listarEstoque() throws SQLException {
        estoques = null;
        estoqueDao.connect();
        this.estoques = estoqueDao.listAll();
        estoqueDao.disconnect();
    }
    
    public void salvarMovimentacao(){
        
    }
    
    public void pesquisar() throws SQLException, ClassNotFoundException {
        List<Estoque> estoquesPesquisa = new ArrayList<>();
        this.listarEstoque();

        if ( descricaoPesquisa.length() == 0 ) {
            return;
        }

        for ( int i = 0; i < this.estoques.size(); i++ ) {
            if ( estoques.get(i).getProduto().getDescricao().indexOf(this.descricaoPesquisa.toUpperCase()) >= 0 ) {
                estoquesPesquisa.add(estoques.get(i));
            }
        }

        this.estoques = estoquesPesquisa;
    }

    public Estoque getEstoqueSelecionado() {
        return estoqueSelecionado;
    }

    public void setEstoqueSelecionado(Estoque estoqueSelecionado) {
        this.estoqueSelecionado = estoqueSelecionado;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    public EstoqueDAO getEstoqueDao() {
        return estoqueDao;
    }

    public void setEstoqueDao(EstoqueDAO estoqueDao) {
        this.estoqueDao = estoqueDao;
    }

    public String getDescricaoPesquisa() {
        return descricaoPesquisa;
    }

    public void setDescricaoPesquisa(String descricaoPesquisa) {
        this.descricaoPesquisa = descricaoPesquisa;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }
    
    
    
    
}

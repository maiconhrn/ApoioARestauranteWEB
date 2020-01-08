/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.EstoqueDAO;
import br.uem.apoioarestaurante.dao.UsuarioDAO;
import br.uem.apoioarestaurante.dao.ProdutoDAO;
import br.uem.apoioarestaurante.metadata.entities.Estoque;
import br.uem.apoioarestaurante.metadata.entities.MovimentoEstoque;
import br.uem.apoioarestaurante.metadata.entities.Produto;
import br.uem.apoioarestaurante.metadata.types.MovimentoEstoqueTipo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@Named
@SessionScoped
public class ManterEstoqueController implements Serializable {
    
    private Estoque                estoqueSelecionado = new Estoque();
    private Estoque                estoque            = new Estoque();
    private List<Estoque>          estoques           = new ArrayList<>();
    private EstoqueDAO             estoqueDao         = new EstoqueDAO();
    private Date                   data               = new Date(System.currentTimeMillis());
    private MovimentacaoController movController      = new MovimentacaoController();
    private String                 descricaoPesquisa;     
    private int                    quantidade       ;
    private String                 tipoMovimentacao ;
    
    
    
    public ManterEstoqueController() throws SQLException { 
        this.listarEstoque();        
    }
    
    public void listarEstoque() throws SQLException {
        estoques = null;
        estoqueDao.connect();
        this.estoques = estoqueDao.listAll();
        estoqueDao.disconnect();
    }
    
    public void limparDadosEstoque() throws SQLException{  
        this.estoqueSelecionado = new Estoque();
        //this.estoqueSelecionado.setProduto( new Produto() );        
        this.listarEstoque();        
    }
    
    
    public void salvarEstoque(Estoque estoque){
        estoqueDao.connect();
        estoqueDao.save(estoque);
        estoqueDao.disconnect();                
    }
    
    public void alterarEstoque( Estoque estoque ){
        estoqueDao.connect();
        estoqueDao.update(estoque);
        estoqueDao.disconnect();    
    }
    
    public void alterarQtdeMinima() throws SQLException{
        if( this.estoqueSelecionado == null || this.estoqueSelecionado.getQtdMinima() < 0) return;
        this.alterarEstoque(this.estoqueSelecionado);
        this.limparDadosEstoque();
    }
    
    public void movimentarEstoque() throws SQLException{
        MovimentoEstoque mov = new MovimentoEstoque(); 
        UsuarioDAO  usuarioDao = new UsuarioDAO();
        
        if ( this.quantidade <= 0 || this.tipoMovimentacao == null || this.tipoMovimentacao == "" ){
            this.limparDadosEstoque();
            return;
        }
        if ( this.tipoMovimentacao.equals("saida") ){
            if ((this.estoqueSelecionado.getQtdEmEstoque() - this.quantidade) < 0) return;
            this.estoqueSelecionado.setQtdEmEstoque(this.estoqueSelecionado.getQtdEmEstoque() - this.quantidade);
            mov.setTipo(MovimentoEstoqueTipo.OUT);
            this.estoqueSelecionado.setUltimaSaida(this.data);
        }
        else{
            this.estoqueSelecionado.setQtdEmEstoque(this.estoqueSelecionado.getQtdEmEstoque() + this.quantidade);
            mov.setTipo(MovimentoEstoqueTipo.IN);
            this.estoqueSelecionado.setUltimaEntrada(this.data);
        }
            mov.setAtivo(Boolean.TRUE);
            mov.setData(this.data);
            mov.setQtd(this.quantidade);
            mov.setEstoque(this.estoqueSelecionado);
            usuarioDao.connect();
            mov.setUsuario(usuarioDao.listAll().get(0));
            usuarioDao.disconnect();
            this.alterarEstoque(this.estoqueSelecionado);  
            this.movController.salvarMovimentacao(mov);
            
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

    public void invalidarEstoque() {
        if (estoqueSelecionado != null) {
            if (estoqueSelecionado.getAtivo()) {
                EstoqueDAO estoqueDAO = EstoqueDAO.getInstance();
                estoqueDAO.connect();
                MovimentoEstoque movimentoEstoque = estoqueSelecionado.invalidar(null);
                estoqueDAO.saveMovimentoEstoque(movimentoEstoque);
                estoqueDAO.update(movimentoEstoque.getEstoque());
                estoqueDAO.disconnect();

                ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
                produtoDAO.connect();
                produtoDAO.update(estoqueSelecionado.getProduto());
                produtoDAO.disconnect();

                return;
            }

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro!", "Estoque já está inválido."));

            return;
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "É necessário selecionar um Estoque na tabela para essa ação."));
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }  
    
}

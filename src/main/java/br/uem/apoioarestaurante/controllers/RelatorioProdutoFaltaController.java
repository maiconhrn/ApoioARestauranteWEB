
package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.models.ProdutoModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@Named
@SessionScoped
public class RelatorioProdutoFaltaController implements Serializable {
    
    private Date dataInicial;
    private Date dataFinal;
    private ProdutoModel produto;
    private List<ProdutoModel> produtos = new ArrayList<>();
    private boolean materiaPrima;
    private boolean revenda;
    private boolean manufatufado;
    
    public void adicionar(){
        produtos.add(produto);
        produto= new ProdutoModel();
    }
    
    public void gerarRelatorio(){
        
    }

    public boolean isMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(boolean materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public boolean isRevenda() {
        return revenda;
    }

    public void setRevenda(boolean revenda) {
        this.revenda = revenda;
    }

    public boolean isManufatufado() {
        return manufatufado;
    }

    public void setManufatufado(boolean manufatufado) {
        this.manufatufado = manufatufado;
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

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    

    
}


package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.models.ProdutoModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@ManagedBean
@SessionScoped
public class RelatorioProdutoFaltaBean {
    
    private Date dataInicial;
    private Date dataFinal;
    private ProdutoModel produto;
    private List<ProdutoModel> produtos = new ArrayList<>();
    
    public void adicionar(){
        produtos.add(produto);
        produto= new ProdutoModel();
    }
    
    public void gerarRelatorio(){
        
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
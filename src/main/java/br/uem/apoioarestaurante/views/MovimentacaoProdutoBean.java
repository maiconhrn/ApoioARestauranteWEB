
package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.models.ProdutoModel;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@ManagedBean
@SessionScoped
public class MovimentacaoProdutoBean {
    
    private int codigoProduto;
    private double quantidade;
    private String tipoMovimentacao;
    private String motivoMovimentacao;
    public final Date data = new Date( System.currentTimeMillis() );

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
    

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    
    
    
    public void SalvarMovimentacao(){
        
    }

    public String getMotivoMovimentacao() {
        return motivoMovimentacao;
    }

    public void setMotivoMovimentacao(String motivoMovimentacao) {
        this.motivoMovimentacao = motivoMovimentacao;
    }
    
    

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    } 

    
    public void CarregarProduto(){
        
    }
            
}

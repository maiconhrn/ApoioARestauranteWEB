
package br.uem.apoioarestaurante.controllers;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@Named
@SessionScoped
public class MovimentacaoController implements Serializable {

    private int codigoProduto;
    private double quantidade;
    private String tipoMovimentacao;
    private String motivoMovimentacao;
    private final Date data = new Date(System.currentTimeMillis());

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


    public void SalvarMovimentacao() {

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


    public void CarregarProduto() {

    }

}

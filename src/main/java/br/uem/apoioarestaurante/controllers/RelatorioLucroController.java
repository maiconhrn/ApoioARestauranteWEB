package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.LucroDia;
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
public class RelatorioLucroController implements Serializable {
    
    private Date dataInicial;
    private Date dataFinal;
    private List<LucroDia> listaTotais = new ArrayList<>();
    
    public List<LucroDia> retornaTotais(){
        
        return listaTotais;
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


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.metadata.entities;

import java.util.Date;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */
public class LucroDia {
    
    private Date data    = new Date();
    private double totalDia;
    private double custo;
    private double lucroDia;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getTotalDia() {
        return totalDia;
    }

    public void setTotalDia(double totalDia) {
        this.totalDia = totalDia;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getLucroDia() {
        return lucroDia;
    }

    public void setLucroDia(double lucroDia) {
        this.lucroDia = lucroDia;
    }
    
    
    
}

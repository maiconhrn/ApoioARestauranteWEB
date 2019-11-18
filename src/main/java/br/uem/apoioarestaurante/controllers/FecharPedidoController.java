package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.types.PedidoStatusTipo;
import br.uem.apoioarestaurante.models.PedidoModel;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.FecharPedidoView;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * Universidade Estadual de Maringá
 *
 * Autor: José Gabriel Júnior       Ra: 54011
 */

@Named
@SessionScoped
public class FecharPedidoController implements Serializable {
    
    @Inject
    private FecharPedidoView fecharPedidoView;

    @Inject
    private PedidoModel pedidoModel;
    
    private double recebido;
    private double troco;
    
    public String conta(double total){
        troco = recebido - total;
        return FacesUtil.CONFIRMA;
    }
    
    public String closeOrder() {
        Pedido pedidoView = fecharPedidoView.getPedido();
        recebido = 0;
        troco = 0;
        pedidoView.setStatus(PedidoStatusTipo.CLOSED);
        pedidoView.setAtivo(Boolean.FALSE);
        pedidoView.setDataFim(Date.from(Instant.now()));
        pedidoModel.update(pedidoView);
        
        return FacesUtil.ORDER;
    }

    public String cancel() {
        recebido = 0;
        troco = 0;
        return FacesUtil.ORDER;
    }

    public Double getRecebido() {
        return recebido;
    }

    public void setRecebido(Double recebido) {
        this.recebido = recebido;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }
    
    
}

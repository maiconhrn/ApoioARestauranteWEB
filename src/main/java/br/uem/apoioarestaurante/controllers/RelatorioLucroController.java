package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.ItemPedidoDAO;
import br.uem.apoioarestaurante.dao.PedidoDAO;
import br.uem.apoioarestaurante.metadata.entities.ItemPedido;
import br.uem.apoioarestaurante.metadata.entities.LucroDia;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
public class RelatorioLucroController implements Serializable {
    
    private Date dataInicial;
    private Date dataFinal;
    
    private List<LucroDia>   listaLucro  = new ArrayList<>();
    private List<Pedido>     pedidos     = new ArrayList<>();
    private List<ItemPedido> totaisItens = new ArrayList<>();
    
    private PedidoDAO pedidoDao   = new PedidoDAO();
    private ItemPedidoDAO itemDao = new ItemPedidoDAO();
    
    private double totalGeralCusto;
    private double totalGeral;
    private double totalLucro;
    
    
    public void carregarPedidosPeriodo(){
        this.pedidoDao.connect();
        this.pedidos = this.pedidoDao.listAll();
        this.pedidoDao.disconnect();
    }
    
    public void carregarTodosItens(){
        this.itemDao.connect();
        this.totaisItens = this.itemDao.listAll();
        this.itemDao.disconnect();
    }
    
    public List<ItemPedido> carregarItensPedido( long id ){
        List<ItemPedido> listaItens= new ArrayList<>();
        
        for( ItemPedido item : this.totaisItens){
            if ( item.getPedido().getId() == id ) listaItens.add(item);
        } 
        return listaItens;
    }
            
    
        
    public void gerarRelatorio(){         
        List<ItemPedido> listaItens= new ArrayList<>();        
        double totalPedido;
        double custoProdutos;
        
        listaLucro  = new ArrayList<>();
        pedidos     = new ArrayList<>();
        totaisItens = new ArrayList<>();
     
        totalGeralCusto = 0;
        totalGeral      = 0;
        totalLucro      = 0;
        
        carregarPedidosPeriodo();
        carregarTodosItens();              
        
        for( Pedido pedido : this.pedidos ){
            if (pedido.getDataFim() != null){ 
                totalPedido   = 0;
                custoProdutos = 0;
                totalPedido   = pedido.getTotal();
                totalGeral   += pedido.getTotal();
                listaItens = carregarItensPedido( pedido.getId() );
                for( ItemPedido item : listaItens){  
                    custoProdutos += item.getQtdProduto() * item.getProduto().getPrecoCompra();
                }
                totalGeralCusto += custoProdutos;
                adicionarListaLucro( pedido.getDataInicio(), totalPedido, custoProdutos );
            }
        }
        
        for( LucroDia lucro: this.listaLucro ){
            lucro.setLucroDia(lucro.getTotalDia() - lucro.getCusto());
            totalLucro += lucro.getLucroDia();
        }
        
    }        
    
    public void adicionarListaLucro( Date data, double totalPedido, double custoProdutos ){
        boolean encontrou = false;        
        LucroDia novoLucro = new LucroDia();  
        Calendar cal = Calendar.getInstance();
    	cal.setTime(data);
    	int dia = cal.get(Calendar.DAY_OF_MONTH);
    	int mes = cal.get(Calendar.MONTH);
    	int ano = cal.get(Calendar.YEAR);
        int diaLucro;
        int mesLucro;
        int anoLucro;
        
        
        for( LucroDia lucro: this.listaLucro){
            cal.setTime(lucro.getData());
            diaLucro = cal.get(Calendar.DAY_OF_MONTH);
            mesLucro = cal.get(Calendar.MONTH);
            anoLucro = cal.get(Calendar.YEAR);
            
            //if ( lucro.getData().compareTo(data) != 0 ){
            if(dia == diaLucro && mes == mesLucro && ano == anoLucro){
                encontrou = true;
                lucro.setCusto( lucro.getCusto() + custoProdutos );
                lucro.setTotalDia( lucro.getTotalDia() + totalPedido );                
            }
        }
        
        if ( !encontrou ){
            novoLucro.setData(data);
            novoLucro.setTotalDia(totalPedido);
            novoLucro.setCusto(custoProdutos);
            this.listaLucro.add(novoLucro);
        }
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

    public List<LucroDia> getListaLucro() {
        return listaLucro;
    }

    public void setListaLucro(List<LucroDia> listaLucro) {
        this.listaLucro = listaLucro;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ItemPedido> getTotaisItens() {
        return totaisItens;
    }

    public void setTotaisItens(List<ItemPedido> totaisItens) {
        this.totaisItens = totaisItens;
    }

    public PedidoDAO getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDAO pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public ItemPedidoDAO getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemPedidoDAO itemDao) {
        this.itemDao = itemDao;
    }

    public double getTotalGeralCusto() {
        return totalGeralCusto;
    }

    public void setTotalGeralCusto(double totalGeralCusto) {
        this.totalGeralCusto = totalGeralCusto;
    }

    public double getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(double totalGeral) {
        this.totalGeral = totalGeral;
    }

    public double getTotalLucro() {
        return totalLucro;
    }

    public void setTotalLucro(double totalLucro) {
        this.totalLucro = totalLucro;
    }    
    
}


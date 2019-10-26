/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.dao.generic.impl.ContasAPagarDAO;
import br.uem.apoioarestaurante.models.ContasAPagar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Master
 */
@Named(value = "contasAPagar")
@Dependent
public class ContasAPagarView {

    private ContasAPagar contaAPagar;
    private ContasAPagarDAO contaAPagarDAO;
    List<ContasAPagar> contasAPagar;
    private Date date7;
    private List<Date> multi;
    private List<Date> range;
    private List<Date> invalidDates;
    private List<Integer> invalidDays;
    private Date minDate;
    private Date maxDate;
    
    private String text;
 
    public ContasAPagarView(){
        contaAPagar = new ContasAPagar();
        contaAPagarDAO = new ContasAPagarDAO();
        contasAPagar = null;
        contasAPagar = contaAPagarDAO.listAll();
        
    }
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    @PostConstruct
    public void init() {
        invalidDates = new ArrayList<>();
        Date today = new Date();
        invalidDates.add(today);
        long oneDay = 24 * 60 * 60 * 1000;
        for (int i = 0; i < 5; i++) {
            invalidDates.add(new Date(invalidDates.get(i).getTime() + oneDay));
        }
 
        invalidDays = new ArrayList<>();
        invalidDays.add(0); /* the first day of week is disabled */
        invalidDays.add(3);
 
        minDate = new Date(today.getTime() - (365 * oneDay));
        maxDate = new Date(today.getTime() + (365 * oneDay));
    }
 
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
 
    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }
    
    public Date getDate7() {
        return date7;
    }
 
    public void setDate7(Date date7) {
        this.date7 = date7;
    }
    public List<Date> getMulti() {
        return multi;
    }
 
    public void setMulti(List<Date> multi) {
        this.multi = multi;
    }
 
    public List<Date> getRange() {
        return range;
    }
 
    public void setRange(List<Date> range) {
        this.range = range;
    }
 
    public List<Date> getInvalidDates() {
        return invalidDates;
    }
 
    public void setInvalidDates(List<Date> invalidDates) {
        this.invalidDates = invalidDates;
    }
 
    public List<Integer> getInvalidDays() {
        return invalidDays;
    }
 
    public void setInvalidDays(List<Integer> invalidDays) {
        this.invalidDays = invalidDays;
    }
 
    public Date getMinDate() {
        return minDate;
    }
 
    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }
 
    public Date getMaxDate() {
        return maxDate;
    }
 
    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public void Novo(){
        contaAPagarDAO.connect();
        contaAPagar.setAtivo(Boolean.TRUE);   
        contaAPagarDAO.save(contaAPagar);
        contaAPagarDAO.disconnect();
        contaAPagarDAO = new ContasAPagarDAO();
    }
    
    public void Alterar(){
        contaAPagarDAO.connect();
        contaAPagarDAO.update(contaAPagar);
        contaAPagarDAO.disconnect();
        contaAPagarDAO = new ContasAPagarDAO();
    }
    
    public void Deletar(){
        contaAPagarDAO.connect();
        contaAPagarDAO.delete(contaAPagar);
        contaAPagarDAO.disconnect();
        contaAPagarDAO = new ContasAPagarDAO();
    }
    
    public void MudarStatus(){
    }
    
    public void Voltar(){
    }
}

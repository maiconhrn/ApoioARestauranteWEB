/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.dao.generic.impl.ContasAPagarDAO;
import br.uem.apoioarestaurante.models.ContasAPagar;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

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
    
    public ContasAPagarView() {
    }

    public void Novo(){
    }
    
    public void Alterar(){
    }
    
    public void Deletar(){
    }
    
    public void MudarStatus(){
    }
    
    public void Voltar(){
    }
}

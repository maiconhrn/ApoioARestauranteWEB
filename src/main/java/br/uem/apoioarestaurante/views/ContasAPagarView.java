package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.dao.generic.impl.ContasAPagarDAO;
import br.uem.apoioarestaurante.models.ContasAPagar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Master
 */

@ManagedBean
@SessionScoped
public class ContasAPagarView {

    private ContasAPagar conta;
    private ContasAPagarDAO contaAPagarDAO;
    List<ContasAPagar> contas;
 
    public ContasAPagarView(){
        conta = new ContasAPagar();
        contaAPagarDAO = new ContasAPagarDAO();
        contas = null;
        contas = contaAPagarDAO.listAll();
        
    }

    public ContasAPagar getContaAPagar() {
        return conta;
    }

    public ContasAPagar getConta() {
        return conta;
    }

    public void setConta(ContasAPagar conta) {
        this.conta = conta;
    }

    public ContasAPagarDAO getContaAPagarDAO() {
        return contaAPagarDAO;
    }

    public void setContaAPagarDAO(ContasAPagarDAO contaAPagarDAO) {
        this.contaAPagarDAO = contaAPagarDAO;
    }

    public List<ContasAPagar> getContas() {
        return contas;
    }

    public void setContas(List<ContasAPagar> contas) {
        this.contas = contas;
    }


    public void setContasAPagar(List<ContasAPagar> contas) {
        this.contas = contas;
    }
    

    public void inserirContasAPagar(ContasAPagar dado){
        contaAPagarDAO.connect();
        conta.setAtivo(Boolean.TRUE);   
        contaAPagarDAO.save(dado);
        contaAPagarDAO.disconnect();
        conta = new ContasAPagar();
    }
    
    public void alterarContasAPagar(ContasAPagar dado){
        contaAPagarDAO.connect();
        contaAPagarDAO.update(dado);
        contaAPagarDAO.disconnect();
        conta = new ContasAPagar();
    }
    
    public void excluirContasAPagar(ContasAPagar dado){
        contaAPagarDAO.connect();
        conta.setAtivo(Boolean.FALSE);
        contaAPagarDAO.update(dado);
        //contaAPagarDAO.delete(dado);
        contaAPagarDAO.disconnect();
        conta = new ContasAPagar();
    }
    
    public void listarContasAPagar() throws SQLException {
        contas = null;
        contaAPagarDAO.connect();
        contas = contaAPagarDAO.listAll();
        contaAPagarDAO.disconnect();
    }
    
    public void buscarConta(String nome) throws SQLException {
        contas = null;
        ContasAPagar cont = new ContasAPagar();
        List<ContasAPagar> resultado = new ArrayList<>();
        contaAPagarDAO.connect();
        contas = contaAPagarDAO.listAll();
        for (int i = 0; i < contas.size(); i++) {
            cont = contas.get(i);
            if (cont.getDescricao().startsWith(nome)) {
                System.out.println(cont.getDescricao());
                resultado.add(cont);
            }
        }
        contas.clear();
        contas = resultado;
        contaAPagarDAO.disconnect();
        conta = new ContasAPagar();
    }
    
    public void limparDados(){
        conta = new ContasAPagar();
    }
    
    public String retornar() throws SQLException{
        listarContasAPagar();
        return"contasAPagar";
    }
}

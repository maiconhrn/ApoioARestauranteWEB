package br.uem.apoioarestaurante.controller;


import br.uem.apoioarestaurante.dao.generic.impl.PermissaoDAO;
import br.uem.apoioarestaurante.models.Permissao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */

@ManagedBean
@SessionScoped
public class PermissaoController {
 
    private Permissao per;
    private PermissaoDAO perDAO;
    private List<Permissao> permissoes;
            
    public PermissaoController() {
        per = new Permissao();
        perDAO = new PermissaoDAO();
        permissoes = null;
        perDAO.connect();
        permissoes = perDAO.listAll();
        perDAO.disconnect();
        
    }
    
    public void inserirPermissao(Permissao permi) throws SQLException, ClassNotFoundException {
        perDAO.connect();
        perDAO.save(permi);
        perDAO.disconnect();
        per = new Permissao();
        
    }
    
    public void alterarPermissao(Permissao permi) throws ClassNotFoundException, SQLException {
        perDAO.connect();
        perDAO.update(permi);
        perDAO.disconnect();
        per = new Permissao();
    }
    
    public void excluirPermissao(Permissao permi) throws SQLException {
        perDAO.connect();
        perDAO.delete(permi);
        perDAO.disconnect();
        per = new Permissao();
    }
    
    public void listarPermissoes() throws SQLException {
        permissoes = null;
        perDAO.connect();
        permissoes = perDAO.listAll();
        perDAO.disconnect();
    }
    
    public void limparDados(){
        per = new Permissao();
    }
    
    public String retornar() throws SQLException{
        listarPermissoes();
        return"permissoes";
    }
    
    public void buscarPermissao(String descricao) throws SQLException {
        permissoes = null;
        Permissao permi = new Permissao();
        List<Permissao> resultado = new ArrayList<>();
        perDAO.connect();
        permissoes = perDAO.listAll();
        for (int i = 0; i < permissoes.size(); i++) {
            permi = permissoes.get(i);
            if (permi.getDescricao().contains(descricao)) {
                System.out.println(permi.getId());
                resultado.add(permi);
            }
        }
        permissoes.clear();
        permissoes = resultado;
        perDAO.disconnect();
        per = new Permissao();
    }

    public Permissao getPer() {
        return per;
    }

    public void setPer(Permissao per) {
        this.per = per;
    }

    public PermissaoDAO getPerDAO() {
        return perDAO;
    }

    public void setPerDAO(PermissaoDAO perDAO) {
        this.perDAO = perDAO;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    
}


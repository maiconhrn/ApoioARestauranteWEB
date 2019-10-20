package br.uem.apoioarestaurante.views;


import br.uem.apoioarestaurante.dao.generic.impl.GrupoPermissaoDAO;
import br.uem.apoioarestaurante.models.Grupopermissao;
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
public class GrupoPermissaoBean {
 
    private Grupopermissao gruper;
    private GrupoPermissaoDAO gruperDAO;
    private List<Grupopermissao> grupos;
            
    public GrupoPermissaoBean() {
        gruper = new Grupopermissao();
        gruperDAO = new GrupoPermissaoDAO();
        grupos= null;
        gruperDAO.connect();
        grupos = gruperDAO.listAll();
        gruperDAO.disconnect();
        
    }
    
    public void inserirGruposPermissao(Grupopermissao grupermi) throws SQLException, ClassNotFoundException {
        gruperDAO.connect();
        gruperDAO.save(grupermi);
        gruperDAO.disconnect();
        gruper = new Grupopermissao();
        
    }
    
    public void alterarGrupoPermissao(Grupopermissao grupermi) throws ClassNotFoundException, SQLException {
        gruperDAO.connect();
        gruperDAO.update(grupermi);
        gruperDAO.disconnect();
        gruper = new Grupopermissao();
    }
    
    public void excluirGrupoPermissao(Grupopermissao grupermi) throws SQLException {
        gruperDAO.connect();
        gruperDAO.delete(grupermi);
        gruperDAO.disconnect();
        gruper = new Grupopermissao();
    }
    
    public void listarGrupoPermissoes() throws SQLException {
        grupos = null;
        gruperDAO.connect();
        grupos = gruperDAO.listAll();
        gruperDAO.disconnect();
    }
    
    public void limparDados(){
        gruper = new Grupopermissao();
    }
    
    public String retornar() throws SQLException{
        listarGrupoPermissoes();
        return"grupoPermissoes";
    }
    
    public void buscarPermissao(String descricao) throws SQLException {
        grupos = null;
        Grupopermissao grupermi = new Grupopermissao();
        List<Grupopermissao> resultado = new ArrayList<>();
        gruperDAO.connect();
        grupos = gruperDAO.listAll();
        for (int i = 0; i < grupos.size(); i++) {
            grupermi = grupos.get(i);
            if (grupermi.getDescricao().contains(descricao)) {
                System.out.println(grupermi.getId());
                resultado.add(grupermi);
            }
        }
        grupos.clear();
        grupos = resultado;
        gruperDAO.disconnect();
        gruper = new Grupopermissao();
    }
    
    public void apagaPermissao(List<Permissao> lista, Permissao permissao){
        lista.remove(permissao);
    }
    
    public String ir(){
        return "atualizaGrupoPermissoes";
    }
    
    public String adicionaPermissao(Grupopermissao gruper, Permissao permissao){
        
        List<Permissao> retorno = new ArrayList();
        retorno= gruper.getPermissoes();
        retorno.add(permissao);
        gruper.setPermissoes(retorno); 
        return "cadastraGrupoPermissoes";
    } 

    public Grupopermissao getGruper() {
        return gruper;
    }

    public void setGruper(Grupopermissao gruper) {
        this.gruper = gruper;
    }

    public GrupoPermissaoDAO getGruperDAO() {
        return gruperDAO;
    }

    public void setGruperDAO(GrupoPermissaoDAO gruperDAO) {
        this.gruperDAO = gruperDAO;
    }

    public List<Grupopermissao> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupopermissao> grupos) {
        this.grupos = grupos;
    }
    
    
    
}


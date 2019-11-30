package br.uem.apoioarestaurante.controllers;


import br.uem.apoioarestaurante.dao.GrupoPermissaoDAO;
import br.uem.apoioarestaurante.models.GrupoPermissao;
import br.uem.apoioarestaurante.models.Permissao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Universidade Estadual de Maringá
 *
 * Autor: José Gabriel Júnior       Ra: 54011
 */

@Named
@SessionScoped
public class GrupoPermissaoController implements Serializable {

    private GrupoPermissao gruper;
    private GrupoPermissaoDAO gruperDAO;
    private List<GrupoPermissao> grupos;

    public GrupoPermissaoController() {
        gruper = new GrupoPermissao();
        gruperDAO = new GrupoPermissaoDAO();
        grupos = null;
        gruperDAO.connect();
        grupos = gruperDAO.listAll();
        gruperDAO.disconnect();

    }

    public void inserirGruposPermissao(GrupoPermissao grupermi) throws SQLException, ClassNotFoundException {
        gruperDAO.connect();
        gruperDAO.save(grupermi);
        gruperDAO.disconnect();
        gruper = new GrupoPermissao();

    }

    public void alterarGrupoPermissao(GrupoPermissao grupermi) throws ClassNotFoundException, SQLException {
        gruperDAO.connect();
        gruperDAO.update(grupermi);
        gruperDAO.disconnect();
        gruper = new GrupoPermissao();
    }

    public void excluirGrupoPermissao(GrupoPermissao grupermi) throws SQLException {
        gruperDAO.connect();
        gruperDAO.delete(grupermi);
        gruperDAO.disconnect();
        gruper = new GrupoPermissao();
    }

    public void listarGrupoPermissoes() throws SQLException {
        grupos = null;
        gruperDAO.connect();
        grupos = gruperDAO.listAll();
        gruperDAO.disconnect();
    }

    public void limparDados() {
        gruper = new GrupoPermissao();
    }

    public String retornar() throws SQLException {
        listarGrupoPermissoes();
        return "grupoPermissoes";
    }

    public void buscarPermissao(String descricao) throws SQLException {
        grupos = null;
        GrupoPermissao grupermi = new GrupoPermissao();
        List<GrupoPermissao> resultado = new ArrayList<>();
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
        gruper = new GrupoPermissao();
    }

    public void apagaPermissao(Permissao permissao) {
        List<Permissao> lista = new ArrayList<>();
        // Permissao teste = new Permissao();
        lista = gruper.getPermissoes();
        lista.remove(permissao);
        gruper.setPermissoes(lista);
    }

    public void adicionaPermissao(Permissao permissao, GrupoPermissao grupo) {
        List<Permissao> lista;
        Permissao teste = new Permissao();
        try {
            lista = grupo.getPermissoes();
            for (int i = 0; i < lista.size(); i++) {
                teste = lista.get(i);
                if (teste.getId().equals(permissao.getId())) {
                    break;
                }
            }
            if (!teste.getId().equals(permissao.getId())) {
                lista.add(permissao);
                gruper.setPermissoes(lista);
            }
        } catch (NullPointerException e) {
            if ((grupo.getId() == null)) {
                if (gruper.getPermissoes() == null) {
                    System.out.println("aqui1");
                    lista = new ArrayList<>();
                    lista.add(permissao);
                    System.out.println(permissao.getDescricao() + "aqui4");
                    grupo.setPermissoes(lista);
                } else {
                    System.out.println("aqui2");
                    lista = gruper.getPermissoes();
                    for (int i = 0; i < lista.size(); i++) {
                        teste = lista.get(i);
                        if (teste.getId().equals(permissao.getId())) {
                            break;
                        }
                    }
                    if (!teste.getId().equals(permissao.getId())) {
                        lista.add(permissao);
                        gruper.setPermissoes(lista);
                    }
                }
            } else {
                if (gruper.getPermissoes().isEmpty()) {
                    System.out.println("aqui3");
                    lista = new ArrayList<>();
                    lista.add(permissao);
                    grupo.setPermissoes(lista);
                }
            }
        }
    }

    public String criarGrupo(Permissao permissao, GrupoPermissao grupo) {
        List<Permissao> lista;
        Permissao teste = new Permissao();
        lista = new ArrayList<>();
        lista.add(permissao);
        gruper.setPermissoes(lista);
        return "atualizaGrupoPermissoes";
    }


    public String ir() {
        return "atualizaGrupoPermissoes";
    }


    public GrupoPermissao getGruper() {
        return gruper;
    }

    public void setGruper(GrupoPermissao gruper) {
        this.gruper = gruper;
    }

    public GrupoPermissaoDAO getGruperDAO() {
        return gruperDAO;
    }

    public void setGruperDAO(GrupoPermissaoDAO gruperDAO) {
        this.gruperDAO = gruperDAO;
    }

    public List<GrupoPermissao> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoPermissao> grupos) {
        this.grupos = grupos;
    }


}


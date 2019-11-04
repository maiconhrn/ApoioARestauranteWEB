package br.uem.apoioarestaurante.controllers;


import br.uem.apoioarestaurante.dao.UsuarioDAO;
import br.uem.apoioarestaurante.metadata.entities.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Universidade Estadual de Maringá
 * <p>
 * Autor:           José Gabriel Júnior       Ra: 54011
 */
@Named
@SessionScoped
public class UsuarioController implements Serializable {
    private Usuario usuario;
    private UsuarioDAO usuDAO;
    List<Usuario> usuarios;

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = UsuarioDAO.getInstance();
        usuario = new Usuario();
        usuDAO = UsuarioDAO.getInstance();
        usuarios = null;
        usuarios = usuDAO.listAll();
    }

    public String envia(String nomeUsuario, String senha) {
        usuDAO.connect();
        usuarios = usuDAO.listAll();
        usuDAO.disconnect();
        usuario = new Usuario();
        if (usuarios == null) {
            return "";
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                if (usuario.getLogin().contentEquals(nomeUsuario) && usuario.getSenha().contentEquals(senha)) {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                    session.setAttribute("loggedUser", usuario);
                    return "/restricted/home.xhtml?faces-redirect=true";
                }
            }
        }
        return "";
    }

    public void inserirUsuario(Usuario usua) throws SQLException, ClassNotFoundException {
        usuDAO.connect();
        usua.setAtivo(Boolean.TRUE);
        usuDAO.save(usua);
        usuDAO.disconnect();
        usuario = new Usuario();

    }

    public void alterarUsuario(Usuario usua) throws SQLException, ClassNotFoundException {
        usuDAO.connect();
        usuDAO.update(usua);
        usuDAO.disconnect();
        usuario = new Usuario();
    }

    public void excluirUsuario(Usuario usua) throws SQLException, ClassNotFoundException {
        usuDAO.connect();
        usuDAO.delete(usua);
        usuDAO.disconnect();
        usuario = new Usuario();
    }

    public List<Usuario> findAllUser() {
        usuarioDAO.connect();
        List<Usuario> usuarios = usuarioDAO.listAll();
        usuarioDAO.disconnect();

        return usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDAO getUsuDAO() {
        return usuDAO;
    }

    public void setUsuDAO(UsuarioDAO usuDAO) {
        this.usuDAO = usuDAO;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


}

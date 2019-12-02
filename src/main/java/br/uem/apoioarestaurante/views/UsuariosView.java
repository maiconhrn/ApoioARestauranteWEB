package br.uem.apoioarestaurante.views;


import br.uem.apoioarestaurante.dao.UsuarioDAO;
import br.uem.apoioarestaurante.metadata.entities.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
public class UsuariosView {
    private Usuario usuario;
    private UsuarioDAO usuDAO;
    List<Usuario> usuarios;
    private boolean permissoes;

    public boolean isPermissoes() {
        return permissoes;
    }

    public void setPermissoes(boolean permissoes) {
        this.permissoes = permissoes;
    }

    public UsuariosView() {
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
                    return "/restricted/home.xhtml";
                }
            }
        }
        return "";
    }

    public void buscarUsuario(String nome) throws SQLException {
        usuarios = null;
        Usuario usu = new Usuario();
        List<Usuario> resultado = new ArrayList<>();
        usuDAO.connect();
        usuarios = usuDAO.listAll();
        for (int i = 0; i < usuarios.size(); i++) {
            usu = usuarios.get(i);
            if (usu.getNome().startsWith(nome)) {
                System.out.println(usu.getNome());
                resultado.add(usu);
            }
        }
        usuarios.clear();
        usuarios = resultado;
        usuDAO.disconnect();
        usuario = new Usuario();
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

    public void listarUsuarios() throws SQLException {
        usuarios = null;
        usuDAO.connect();
        usuarios = usuDAO.listAll();
        usuDAO.disconnect();
    }

    public void limparDados() {
        usuario = new Usuario();
    }

    public String retornar() throws SQLException {
        listarUsuarios();
        return "usuarios";
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

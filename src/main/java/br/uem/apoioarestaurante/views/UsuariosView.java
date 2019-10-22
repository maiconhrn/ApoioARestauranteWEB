package br.uem.apoioarestaurante.views;


import br.uem.apoioarestaurante.dao.generic.impl.UsuariosDAO;
import br.uem.apoioarestaurante.models.Usuarios;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 *
 * @author GabrielConejo
 */

@ManagedBean
@SessionScoped
public class UsuariosView {
    private Usuarios usuario;
    private UsuariosDAO usuDAO;
    List<Usuarios> usuarios;
    
    public UsuariosView() {
        usuario = new Usuarios();
        usuDAO = new UsuariosDAO();
        usuarios = null;
        usuarios = usuDAO.listAll();
    }

    public String envia(String nomeUsuario, String senha) { 
        usuDAO.connect();
        usuarios = usuDAO.listAll() ;
        usuDAO.disconnect();
        usuario = new Usuarios();
        if (usuarios == null) {
            return "";
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                if (usuario.getLogin().contentEquals(nomeUsuario) && usuario.getPassword().contentEquals(senha)) {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                    session.setAttribute("loggedUser", usuario);
                    return "/restricted/home.xhtml";
                }
            } 
        }
        return "";
    }
    
    public void inserirUsuario(Usuarios usua) throws SQLException, ClassNotFoundException {
        usuDAO.connect();
        usua.setAtivo(Boolean.TRUE);
        usuDAO.save(usua);
        usuDAO.disconnect();
        usuario = new Usuarios();
        
    }
    
    public void alterarUsuario(Usuarios usua) throws SQLException, ClassNotFoundException {
        usuDAO.connect();
        usuDAO.update(usua);
        usuDAO.disconnect();
        usuario = new Usuarios();
    }
    
    public void excluirUsuario(Usuarios usua) throws SQLException, ClassNotFoundException {
        usuDAO.connect();
        usuDAO.delete(usua);
        usuDAO.disconnect();
        usuario = new Usuarios();
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public UsuariosDAO getUsuDAO() {
        return usuDAO;
    }

    public void setUsuDAO(UsuariosDAO usuDAO) {
        this.usuDAO = usuDAO;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
    
    

}

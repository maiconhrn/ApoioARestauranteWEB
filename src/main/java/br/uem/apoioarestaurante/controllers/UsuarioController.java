package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.UsuarioDAO;
import br.uem.apoioarestaurante.metadata.entities.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class UsuarioController implements Serializable {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = UsuarioDAO.getInstance();
    }

    public List<Usuario> findAllUser() {
        usuarioDAO.connect();
        List<Usuario> usuarios = usuarioDAO.listAll();
        usuarioDAO.disconnect();

        return usuarios;
    }
}

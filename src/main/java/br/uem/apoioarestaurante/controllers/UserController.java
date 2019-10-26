package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.UserDAO;
import br.uem.apoioarestaurante.metadata.entities.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class UserController implements Serializable {

    private UserDAO userDAO;

    public UserController() {
        userDAO = UserDAO.getInstance();
    }

    public List<User> findAllUser() {
        userDAO.connect();
        List<User> users = userDAO.listAll();
        userDAO.disconnect();

        return users;
    }
}

package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.User;

/**
 * @author Maicon
 */
public class UserDAO extends HibernateBasicGenericDAO<User> {

    private static UserDAO ourInstance = new UserDAO();

    public static UserDAO getInstance() {
        return ourInstance;
    }

    private UserDAO() {
        super(User.class);
    }

    public User findById(Long id) {
        try {
            requireOpenSession();

            return getSession().get(User.class, id);
        } catch (DAOException e) {
            e.printStackTrace();

            return null;
        }
    }
}

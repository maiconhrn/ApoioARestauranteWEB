package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.Usuario;

/**
 * @author Maicon
 */
public class UsuarioDAO extends HibernateBasicGenericDAO<Usuario> {

    private static UsuarioDAO ourInstance = new UsuarioDAO();

    public static UsuarioDAO getInstance() {
        return ourInstance;
    }

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario findById(Long id) {
        try {
            requireOpenSession();

            return getSession().get(Usuario.class, id);
        } catch (DAOException e) {
            e.printStackTrace();

            return null;
        }
    }
}

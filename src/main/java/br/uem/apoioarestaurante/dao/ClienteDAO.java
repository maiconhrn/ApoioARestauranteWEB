package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.Cliente;

/**
 * @author Maicon
 */
public class ClienteDAO extends HibernateBasicGenericDAO<Cliente> {

    private static ClienteDAO ourInstance = new ClienteDAO();

    public static ClienteDAO getInstance() {
        return ourInstance;
    }

    private ClienteDAO() {
        super(Cliente.class);
    }

    public Cliente findById(Long id) {
        try {
            requireOpenSession();

            return getSession().get(Cliente.class, id);
        } catch (DAOException e) {
            e.printStackTrace();

            return null;
        }
    }
}

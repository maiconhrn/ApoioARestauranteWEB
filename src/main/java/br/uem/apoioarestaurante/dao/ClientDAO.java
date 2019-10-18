package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.metadata.entities.Client;

/**
 * @author Maicon
 */
public class ClientDAO extends HibernateBasicGenericDAO<Client> {

    private static ClientDAO ourInstance = new ClientDAO();

    public static ClientDAO getInstance() {
        return ourInstance;
    }

    private ClientDAO() {
        super(Client.class);
    }

    public Client findById(Long id) {
        try {
            requireOpenSession();

            return getSession().get(Client.class, id);
        } catch (DAOException e) {
            e.printStackTrace();

            return null;
        }
    }
}

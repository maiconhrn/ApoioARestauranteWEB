package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.ClientDAO;
import br.uem.apoioarestaurante.metadata.entities.Client;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@ViewScoped
public class ClientController implements Serializable {

    private ClientDAO clientDAO;

    public ClientController() {
        clientDAO = ClientDAO.getInstance();
    }

    public List<Client> findAllClients() {
        clientDAO.connect();
        List<Client> clients = clientDAO.listAll();
        clientDAO.disconnect();

        return clients;
    }
}

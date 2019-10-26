package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.ClienteDAO;
import br.uem.apoioarestaurante.metadata.entities.Cliente;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@ViewScoped
public class ClienteController implements Serializable {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        clienteDAO = ClienteDAO.getInstance();
    }

    public List<Cliente> findAllClients() {
        clienteDAO.connect();
        List<Cliente> clientes = clienteDAO.listAll();
        clienteDAO.disconnect();

        return clientes;
    }
}

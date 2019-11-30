package br.uem.apoioarestaurante.controllers;


import br.uem.apoioarestaurante.dao.ClienteDAO;
import br.uem.apoioarestaurante.metadata.entities.Cliente;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Universidade Estadual de Maringá
 *
 * Autor: José Gabriel Júnior       Ra: 54011
 */

@Named
@SessionScoped
public class ClienteController implements Serializable {

    private Cliente cli;
    private ClienteDAO cliDAO;
    private List<Cliente> clientes;

    public ClienteController() {
        cli = new Cliente();
        cliDAO = ClienteDAO.getInstance();
        clientes = null;
        cliDAO.connect();
        clientes = cliDAO.listAll();
        cliDAO.disconnect();

    }

    public void inserirCliente(Cliente clien) throws SQLException, ClassNotFoundException {
        cliDAO.connect();
        cliDAO.save(clien);
        cliDAO.disconnect();
        cli = new Cliente();

    }

    public void alterarCliente(Cliente clien) throws ClassNotFoundException, SQLException {
        cliDAO.connect();
        cliDAO.update(cli);
        cliDAO.disconnect();
        cli = new Cliente();
    }

    public void excluirCliente(Cliente clien) throws SQLException {
        cliDAO.connect();
        cliDAO.delete(clien);
        cliDAO.disconnect();
        cli = new Cliente();
    }

    public void buscarCliente(String nome) throws SQLException {
        clientes = null;
        Cliente clien = new Cliente();
        List<Cliente> resultado = new ArrayList<>();
        cliDAO.connect();
        clientes = cliDAO.listAll();
        for (int i = 0; i < clientes.size(); i++) {
            clien = clientes.get(i);
            if (clien.getNome().startsWith(nome)) {
                resultado.add(clien);
            }
        }
        clientes.clear();
        clientes = resultado;
        cliDAO.disconnect();
        cli = new Cliente();
    }

    public void listarClientes() throws SQLException {
        clientes = null;
        cliDAO.connect();
        clientes = cliDAO.listAll();
        cliDAO.disconnect();
    }

    public void limparDados() {
        cli = new Cliente();
    }

    public String retornar() throws SQLException {
        listarClientes();
        return "clientes";
    }

    public List<Cliente> findAllClients() {
        cliDAO.connect();
        List<Cliente> clientes = cliDAO.listAll();
        cliDAO.disconnect();

        return clientes;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public ClienteDAO getCliDAO() {
        return cliDAO;
    }

    public void setCliDAO(ClienteDAO cliDAO) {
        this.cliDAO = cliDAO;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }


}


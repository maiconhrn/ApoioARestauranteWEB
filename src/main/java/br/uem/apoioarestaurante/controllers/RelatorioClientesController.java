package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.reports.RelatorioClientes;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Universidade Estadual de Maringá
 *
 * Autor: José Gabriel Júnior       Ra: 54011
 */

@Named
@ViewScoped
public class RelatorioClientesController implements Serializable {
    RelatorioClientes relatorio = new RelatorioClientes();

    public void generate(Long id) {
        relatorio.generateReport(id);
    }
}

package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.utils.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Maicon
 */
@Named
@ViewScoped
public class RelatorioHistoricoPedidosController implements Serializable {

    public void generate() {

    }

    public String cancel() {
        return FacesUtil.HOME;
    }
}

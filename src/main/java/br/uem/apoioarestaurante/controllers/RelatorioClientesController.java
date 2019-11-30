package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.utils.FacesUtil;

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
    

    public void generate() {

    }

    public String cancel() {
        return FacesUtil.HOME;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.utils.FacesUtil;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author GabrielConejo
 */

@Named
@ViewScoped
public class RelatorioContasAPagarController implements Serializable {

    
    
    public void generate() {

    }

    public String cancel() {
        return FacesUtil.HOME;
    }
}

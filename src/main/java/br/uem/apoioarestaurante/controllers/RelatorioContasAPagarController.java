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
import br.uem.apoioarestaurante.exceptions.ReportException;
import br.uem.apoioarestaurante.reports.MovimentosEstoqueReportFactory;
import br.uem.apoioarestaurante.views.RelatorioContasAPagarView;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Date;

/**
 *
 * @author GabrielConejo
 */

@Named
@ViewScoped
public class RelatorioContasAPagarController implements Serializable {
    
	@Inject
    private RelatorioContasAPagarView relatorioContasAPagarView;


    public void generate() {
        Date dataDe = relatorioContasAPagarView.getBeginFilter();
        Date dataPara = relatorioContasAPagarView.getEndFilter();

        try {
            if (dataDe != null && dataPara != null) {
                MovimentosEstoqueReportFactory reportFactory = new MovimentosEstoqueReportFactory(dataDe, dataPara);
                String reportFilePath = reportFactory.generateReport();

                FacesUtil.downloadFile(reportFilePath);
                return;
            }

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Aviso!", "Por favor, selecione os parâmetros de busca para a geração do relatório"));
        } catch (ReportException e) {
            e.printStackTrace();

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Aviso!", e.getMessage()));
        }
    }

    public String cancel() {
        return FacesUtil.HOME;
    }
}

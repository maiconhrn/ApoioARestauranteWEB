package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.exceptions.ReportException;
import br.uem.apoioarestaurante.reports.MovimentosEstoqueReportFactory;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.RelatorioMovimentacaoEstoqueView;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Maicon
 */
@Named
@ViewScoped
public class RelatorioMovimentacaoEstoqueController implements Serializable {

    @Inject
    private RelatorioMovimentacaoEstoqueView relatorioMovimentacaoEstoqueView;


    public void generate() {
        Date dataDe = relatorioMovimentacaoEstoqueView.getBeginFilter();
        Date dataPara = relatorioMovimentacaoEstoqueView.getEndFilter();

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

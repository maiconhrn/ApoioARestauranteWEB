package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.reports.PedidosReportFactory;
import br.uem.apoioarestaurante.utils.FacesUtil;
import br.uem.apoioarestaurante.views.RelatorioHistoricoPedidosView;

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
public class RelatorioHistoricoPedidosController implements Serializable {

    @Inject
    private RelatorioHistoricoPedidosView relatorioHistoricoPedidosView;

    public void generate() {
        Date dataDe = relatorioHistoricoPedidosView.getBeginFilter();
        Date dataPara = relatorioHistoricoPedidosView.getEndFilter();

        if (dataDe != null && dataPara != null) {
            PedidosReportFactory reportFactory = new PedidosReportFactory(dataDe, dataPara);
            reportFactory.generateReport();

            return;
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", "Por favor, selecione os parâmetros de busca para a geração do relatório"));
    }

    public String cancel() {
        return FacesUtil.HOME;
    }
}

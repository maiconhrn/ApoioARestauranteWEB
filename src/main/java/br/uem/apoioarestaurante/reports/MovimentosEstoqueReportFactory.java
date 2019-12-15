package br.uem.apoioarestaurante.reports;

import br.uem.apoioarestaurante.dao.MovimentoEstoqueDAO;
import br.uem.apoioarestaurante.exceptions.ReportException;
import br.uem.apoioarestaurante.metadata.entities.MovimentoEstoque;
import br.uem.apoioarestaurante.reports.generic.impl.AbstractReportFactory;
import br.uem.apoioarestaurante.reports.generic.resource.ReportResources;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public class MovimentosEstoqueReportFactory extends AbstractReportFactory<MovimentoEstoque> {

    private String reportFontFileName;
    private Date dataDe;
    private Date dataPara;

    public MovimentosEstoqueReportFactory(Date dataDe, Date dataPara) {
        super(MovimentoEstoque.class);
        this.dataDe = dataDe;
        this.dataPara = dataPara;
        reportFontFileName = "movimentos-estoque-report.jrxml";
    }

    @Override
    public List<MovimentoEstoque> load() throws ReportException {
        MovimentoEstoqueDAO movimentoEstoqueDAO = MovimentoEstoqueDAO.getInstance();

        movimentoEstoqueDAO.connect();

        List<MovimentoEstoque> movimentosEstoque = movimentoEstoqueDAO.findInInitialDateRange(dataDe, dataPara);

        if (movimentosEstoque == null || movimentosEstoque.isEmpty()) {
            throw new ReportException("Nenhum resultado foi encontrado para estes filtros");
        }

        movimentoEstoqueDAO.disconnect();

        return movimentosEstoque != null ? movimentosEstoque : new ArrayList<>();
    }

    @Override
    public String generateReport() throws ReportException {
        try {
            InputStream reportFont = this.getClass().getResourceAsStream("/reports/" + reportFontFileName);

            JasperReport report = JasperCompileManager.compileReport(reportFont);
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(load()));

            String reportFilePath = new StringBuilder(ReportResources.REPORTS_DIRECTORY_RESOURCE)
                    .append(getEntityClass().getSimpleName())
                    .append("_Report_")
                    .append(new Date().getTime())
                    .append(".pdf")
                    .toString();

            JasperExportManager.exportReportToPdfFile(print, reportFilePath);

            return reportFilePath;
        } catch (JRException e) {
            e.printStackTrace();
        }

        return "";
    }
}

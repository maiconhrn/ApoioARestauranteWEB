package br.uem.apoioarestaurante.reports;

import br.uem.apoioarestaurante.dao.MovimentoEstoqueDAO;
import br.uem.apoioarestaurante.exceptions.ReportException;
import br.uem.apoioarestaurante.metadata.entities.MovimentoEstoque;
import br.uem.apoioarestaurante.reports.generic.impl.AbstractReportFactory;
import br.uem.apoioarestaurante.reports.resource.ReportResources;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
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

        return movimentosEstoque;
    }

    @Override
    public String generateReport() throws ReportException {
        try {
            InputStream reportFont = this.getClass().getResourceAsStream("/reports/" + reportFontFileName);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("logo", ImageIO.read(this.getClass().getResourceAsStream("/images/logo.png")));

            JasperReport report = JasperCompileManager.compileReport(reportFont);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(load()));

            String reportFilePath = new StringBuilder(ReportResources.REPORTS_DIRECTORY_RESOURCE)
                    .append(getEntityClass().getSimpleName())
                    .append("_Report_")
                    .append(new Date().getTime())
                    .append(".pdf")
                    .toString();

            JasperExportManager.exportReportToPdfFile(print, reportFilePath);

            return reportFilePath;
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}

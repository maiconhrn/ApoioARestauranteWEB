package br.uem.apoioarestaurante.reports;

import br.uem.apoioarestaurante.dao.ContasAPagarDAO;
import br.uem.apoioarestaurante.exceptions.ReportException;
import br.uem.apoioarestaurante.models.ContasAPagar;
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
 * @author GabrielConejo
 */
public class RelatorioContasAPagar extends AbstractReportFactory<ContasAPagar> {

    private String reportFontFileName;

    public RelatorioContasAPagar() {
        super(ContasAPagar.class);
        reportFontFileName = "contas-a-pagar.jrxml";
    }

    @Override
    public List<ContasAPagar> load() throws ReportException {
    	ContasAPagarDAO contasAPagarDAO = ContasAPagarDAO.getInstance();

    	contasAPagarDAO.connect();

        List<ContasAPagar> contasAPagar = contasAPagarDAO.listAll();

        if (contasAPagar == null || contasAPagar.isEmpty()) {
            throw new ReportException("Nenhum resultado foi encontrado para estes filtros");
        }

        contasAPagarDAO.disconnect();

        return contasAPagar;
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
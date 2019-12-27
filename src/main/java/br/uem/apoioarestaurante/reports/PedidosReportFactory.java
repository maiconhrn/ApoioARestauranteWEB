package br.uem.apoioarestaurante.reports;

import br.uem.apoioarestaurante.dao.PedidoDAO;
import br.uem.apoioarestaurante.exceptions.ReportException;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
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
public class PedidosReportFactory extends AbstractReportFactory<Pedido> {

    private String reportFontFileName;
    private String subreportFontFileName;
    private Date dataDe;
    private Date dataPara;

    public PedidosReportFactory(Date dataDe, Date dataPara) {
        super(Pedido.class);
        this.dataDe = dataDe;
        this.dataPara = dataPara;
        reportFontFileName = "pedidos-report.jrxml";
        subreportFontFileName = "item-pedido-subreport.jrxml";
    }

    @Override
    public List<Pedido> load() throws ReportException {
        PedidoDAO pedidoDAO = PedidoDAO.getInstance();

        pedidoDAO.connect();

        List<Pedido> pedidos = pedidoDAO.findInInitialDateRange(dataDe, dataPara);

        if (pedidos == null || pedidos.isEmpty()) {
            throw new ReportException("Nenhum resultado foi encontrado para estes filtros");
        }

        pedidoDAO.disconnect();

        return pedidos;
    }

    @Override
    public String generateReport() throws ReportException {
        try {
            InputStream reportFont = this.getClass().getResourceAsStream("/reports/" + reportFontFileName);
            InputStream subreportFont = this.getClass().getResourceAsStream("/reports/" + subreportFontFileName);

            JasperReport report = JasperCompileManager.compileReport(reportFont);
            JasperReport subreport = JasperCompileManager.compileReport(subreportFont);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("subreport", subreport);
            parameters.put("logo", ImageIO.read(this.getClass().getResourceAsStream("/images/logo.png")));

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

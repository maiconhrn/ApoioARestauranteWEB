package br.uem.apoioarestaurante.reports;

import br.uem.apoioarestaurante.dao.PedidoDAO;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.reports.generic.impl.AbstractReportFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.ArrayList;
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
        reportFontFileName = "pedidos_report.jrxml";
        subreportFontFileName = "item-pedido-subreport.jrxml";
    }

    @Override
    public List<Pedido> load() {
        PedidoDAO pedidoDAO = PedidoDAO.getInstance();

        pedidoDAO.connect();

        List<Pedido> pedidos = pedidoDAO.findInInitialDateRange(dataDe, dataPara);

        pedidoDAO.disconnect();

        return pedidos != null ? pedidos : new ArrayList<>();
    }

    @Override
    public void generateReport() {
        try {
            InputStream reportFont = this.getClass().getResourceAsStream("/reports/" + reportFontFileName);
            InputStream subreportFont = this.getClass().getResourceAsStream("/reports/" + subreportFontFileName);

            JasperReport report = JasperCompileManager.compileReport(reportFont);
            JasperReport subreport = JasperCompileManager.compileReport(subreportFont);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("subreport", subreport);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(load()));
            JasperExportManager.exportReportToPdfFile(print, new StringBuilder("C:\\aar\\reports\\")
                    .append(getEntityClass().getSimpleName())
                    .append("_Report_")
                    .append(new Date().getTime())
                    .append(".pdf")
                    .toString());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}

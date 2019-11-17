package br.uem.apoioarestaurante.reports;

import br.uem.apoioarestaurante.dao.PedidoDAO;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.reports.generic.impl.AbstractReportFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public class TestPedidosReportFactory extends AbstractReportFactory<Pedido> {

    private String reportFontFileName;

    public TestPedidosReportFactory() {
        super(Pedido.class);
        reportFontFileName = "test_pedidos_report.jrxml";
    }

    @Override
    public List<Pedido> load() {
        PedidoDAO pedidoDAO = PedidoDAO.getInstance();

        pedidoDAO.connect();

        List<Pedido> pedidos = pedidoDAO.listAll();

        pedidoDAO.disconnect();

        return pedidos != null ? pedidos : new ArrayList<>();
    }

    @Override
    public void generateReport() {
        try {
            InputStream reportFont = this.getClass().getResourceAsStream("/reports/" + reportFontFileName);

            JasperReport report = JasperCompileManager.compileReport(reportFont);
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(load()));
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

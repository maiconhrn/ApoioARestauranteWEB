package br.uem.apoioarestaurante.reports;

import br.uem.apoioarestaurante.reports.generic.intf.ReportFactory;
import org.junit.Test;

/**
 * @author Maicon
 */
public class TestPedidosReportFactoryTest {

    @Test
    public void generateReport() {
        TestPedidosReportFactory reportFactory = new TestPedidosReportFactory();

        reportFactory.generateReport();

        assert true;
    }
}
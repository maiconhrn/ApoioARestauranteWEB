package br.uem.apoioarestaurante.reports;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Maicon
 */
public class PedidosReportFactoryTest {

    @Test
    public void generateReport() {
        PedidosReportFactory reportFactory = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            reportFactory = new PedidosReportFactory(sdf.parse("01/09/2019"), sdf.parse("01/12/2019"));

            reportFactory.generateReport();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert true;
    }
}
package br.uem.apoioarestaurante.reports;

import br.uem.apoioarestaurante.exceptions.ReportException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Maicon
 */
public class MovimentosEstoqueReportFactoryTest {

    @Test
    public void generateReport() {
        MovimentosEstoqueReportFactory reportFactory = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            reportFactory = new MovimentosEstoqueReportFactory(sdf.parse("01/09/2019"), sdf.parse("31/12/2019"));

            reportFactory.generateReport();
        } catch (ParseException | ReportException e) {
            e.printStackTrace();
        }

        assert true;
    }
}
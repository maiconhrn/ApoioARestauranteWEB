package br.uem.apoioarestaurante.reports.generic.intf;

import br.uem.apoioarestaurante.exceptions.ReportException;

import java.util.List;

/**
 * @author Maicon
 */
public interface ReportFactory<T> {
    List<T> load() throws ReportException;

    String generateReport() throws ReportException;
}

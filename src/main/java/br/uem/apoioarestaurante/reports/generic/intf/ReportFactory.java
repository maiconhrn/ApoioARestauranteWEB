package br.uem.apoioarestaurante.reports.generic.intf;

import java.util.List;

/**
 * @author Maicon
 */
public interface ReportFactory<T> {
    List<T> load();

    void generateReport();
}

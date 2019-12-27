package br.uem.apoioarestaurante.reports.generic.impl;

import br.uem.apoioarestaurante.exceptions.ReportException;
import br.uem.apoioarestaurante.reports.generic.intf.ReportFactory;

import java.util.List;

/**
 * @author Maicon
 */
public abstract class AbstractReportFactory<T> implements ReportFactory<T> {

    private Class<T> entityClass;

    protected AbstractReportFactory(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public List<T> load() throws ReportException {
        throw new UnsupportedOperationException("You have to implement this method");
    }

    @Override
    public String generateReport() throws ReportException {
        throw new UnsupportedOperationException("You have to implement this method");
    }
}

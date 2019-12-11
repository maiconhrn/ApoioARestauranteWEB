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
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */
public class RelatorioClientes extends AbstractReportFactory<Pedido> {

    private String reportFontFileName;

    public RelatorioClientes() {
        super(Pedido.class);
        reportFontFileName = "relatorioCliente.jrxml";
    }


    public List<Pedido> load(long id) {
        
        PedidoDAO pedidoDAO = PedidoDAO.getInstance();
        Pedido ped = new Pedido();

        pedidoDAO.connect();

        List<Pedido> pedidos = pedidoDAO.listAll();
        List<Pedido> retorno = new ArrayList<>();

        pedidoDAO.disconnect();
        
        for (int i = 0; i < pedidos.size(); i++) {
            ped = pedidos.get(i);
            if (ped.getCliente().getId()== id) {
                retorno.add(ped);
            }
        }
        return retorno != null ? retorno : new ArrayList<>();
    }


    public void generateReport(long Id) {
        try {
            InputStream reportFont = this.getClass().getResourceAsStream("/reports/" + reportFontFileName);

            JasperReport report = JasperCompileManager.compileReport(reportFont);
            
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(load(Id)));
            
            JasperExportManager.exportReportToPdfFile(print, new StringBuilder("C:\\Users\\Gabriel\\Desktop\\aar\\reports\\")
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

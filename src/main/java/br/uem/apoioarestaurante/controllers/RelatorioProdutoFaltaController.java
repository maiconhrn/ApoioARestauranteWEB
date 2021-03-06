
package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.EstoqueDAO;
import br.uem.apoioarestaurante.exceptions.ReportException;
import br.uem.apoioarestaurante.metadata.entities.Estoque;
import br.uem.apoioarestaurante.metadata.types.ProdutoTipo;
import br.uem.apoioarestaurante.reports.PedidosReportFactory;
import br.uem.apoioarestaurante.reports.resource.ReportResources;
import br.uem.apoioarestaurante.utils.FacesUtil;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@Named
@SessionScoped
public class RelatorioProdutoFaltaController implements Serializable {
    
    private Date dataInicial;
    private Date dataFinal;
    private Estoque estoque;
    private List<Estoque> estoques = new ArrayList<>();
    private List<Estoque> todoEstoque = new ArrayList<>();
    private EstoqueDAO estoqueDao;
    private boolean materiaPrima;
    private boolean revenda;
    private boolean manufatufado;
    private String reportFontFileName = "produtos-falta.jrxml";

    public RelatorioProdutoFaltaController() {      
        this.inicializar();
    } 
    
    public void inicializar(){
        this.estoque      = new Estoque();        
        this.estoqueDao   = new EstoqueDAO();        
        this.materiaPrima = false;
        this.revenda      = false;
        this.manufatufado = false;
    }
    
    public void listarEstoque(){
        estoqueDao.connect();
        todoEstoque = estoqueDao.listAll();
        estoqueDao.disconnect();
    }    
    
    
    public void gerarRelatorio(){
        todoEstoque = new ArrayList<>();
        estoques    = new ArrayList<>();
        
        if( materiaPrima || revenda || manufatufado){ 
            this.listarEstoque();
            for (Estoque est : todoEstoque) {
                if (est.getProduto().getAtivo() && est.getQtdMinima() >= est.getQtdEmEstoque()){
                    if (est.getProduto().getTipo().equals(ProdutoTipo.FEEDSTOCK) && materiaPrima) estoques.add( est );
                    else if (est.getProduto().getTipo().equals(ProdutoTipo.MANUFACTURED) && manufatufado ) estoques.add( est );
                    else if (est.getProduto().getTipo().equals(ProdutoTipo.RESALE) && revenda ) estoques.add( est );
                }
            }
                
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Selecione pelo menos um tipo de produto!"));
        }
        
        inicializar();
    }
    
    public String generateReport( ) throws ReportException {
        try {
            InputStream reportFont = this.getClass().getResourceAsStream("/reports/" + reportFontFileName);
            //InputStream subreportFont = this.getClass().getResourceAsStream("/reports/" + subreportFontFileName);

            JasperReport report = JasperCompileManager.compileReport(reportFont);
            //JasperReport subreport = JasperCompileManager.compileReport(subreportFont);

            HashMap<String, Object> parameters = new HashMap<>();
           // parameters.put("subreport", subreport);
            parameters.put("logo", ImageIO.read(this.getClass().getResourceAsStream("/images/logo.png")));

            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(this.getEstoques()));

            String reportFilePath = new StringBuilder(ReportResources.REPORTS_DIRECTORY_RESOURCE)
                    .append("teste")
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

    public String cancel() {
        return FacesUtil.HOME;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    public EstoqueDAO getEstoqueDao() {
        return estoqueDao;
    }

    public void setEstoqueDao(EstoqueDAO estoqueDao) {
        this.estoqueDao = estoqueDao;
    }

    public boolean isMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(boolean materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public boolean isRevenda() {
        return revenda;
    }

    public void setRevenda(boolean revenda) {
        this.revenda = revenda;
    }

    public boolean isManufatufado() {
        return manufatufado;
    }

    public void setManufatufado(boolean manufatufado) {
        this.manufatufado = manufatufado;
    }

  


    
}

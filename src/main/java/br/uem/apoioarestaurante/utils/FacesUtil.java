package br.uem.apoioarestaurante.utils;

import br.uem.apoioarestaurante.reports.resource.ReportResources;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

/**
 * @author Maicon
 */
public class FacesUtil {

    public static String LOGIN = "/security/login?faces-redirect=true";
    public static String HOME = "/restricted/home?faces-redirect=true";
    public static String ORDER = "/restricted/pedido?faces-redirect=true";
    public static String ORDER_MAINTENANCE = "/restricted/manutencao-pedido?faces-redirect=true";
    public static String ORDER_ITEM_PRODUCT = "/restricted/produto-item-pedido?faces-redirect=true";
    public static String FECHAR_PEDIDO = "/restricted/fechar-pedido?faces-redirect=true";
    public static String CONFIRMA = "/restricted/fechar-pedido_confirma?faces-redirect=true";

    public static String getParam(String paramName) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        return paramMap.get(paramName);
    }

    public static void downloadFile(String filePath) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();

            String fileName = "Relatorio_" + new Date().getTime() + ".pdf";

            externalContext.responseReset();
            externalContext.setResponseContentType(ReportResources.REPORTS_DEFAULT_CONTENT_TYPE);
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            FileInputStream inputStream = new FileInputStream(new File(filePath));
            OutputStream outputStream = externalContext.getResponseOutputStream();

            byte[] buffer = new byte[1024];

            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();

            facesContext.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

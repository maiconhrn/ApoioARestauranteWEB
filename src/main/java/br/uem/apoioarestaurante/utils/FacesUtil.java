package br.uem.apoioarestaurante.utils;

import javax.faces.context.FacesContext;
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
}

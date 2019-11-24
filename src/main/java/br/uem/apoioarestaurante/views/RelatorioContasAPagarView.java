package br.uem.apoioarestaurante.views;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@ViewScoped
public class RelatorioContasAPagarView implements Serializable {

    private Date beginFilter;
    private Date endFilter;

    public Date getBeginFilter() {
        return beginFilter;
    }

    public void setBeginFilter(Date beginFilter) {
        this.beginFilter = beginFilter;
    }

    public Date getEndFilter() {
        return endFilter;
    }

    public void setEndFilter(Date endFilter) {
        this.endFilter = endFilter;
    }
}

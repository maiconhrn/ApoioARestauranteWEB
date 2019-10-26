package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.controllers.ProdutoItemPedidoController;
import br.uem.apoioarestaurante.metadata.entities.Produto;
import br.uem.apoioarestaurante.models.PedidoModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@SessionScoped
public class ProdutoItemPedidoView implements Serializable {

    @Inject
    private ProdutoItemPedidoController produtoItemPedidoController;

    @Inject
    private PedidoModel pedidoModel;

    private boolean idFilterSelected;
    private boolean descriptionFilterSelected;
    private String idFilter;
    private String descriptionFilter;
    private List<Produto> produtos = new ArrayList<>();
    private Produto selectedProduto;

    @PostConstruct
    public void init() {
        produtos = new ArrayList<>();
    }

    public boolean isIdFilterSelected() {
        return idFilterSelected;
    }

    public void setIdFilterSelected(boolean idFilterSelected) {
        this.idFilterSelected = idFilterSelected;
    }

    public boolean isDescriptionFilterSelected() {
        return descriptionFilterSelected;
    }

    public void setDescriptionFilterSelected(boolean descriptionFilterSelected) {
        this.descriptionFilterSelected = descriptionFilterSelected;
    }

    public String getIdFilter() {
        return idFilter;
    }

    public void setIdFilter(String idFilter) {
        this.idFilter = idFilter;
    }

    public String getDescriptionFilter() {
        return descriptionFilter;
    }

    public void setDescriptionFilter(String descriptionFilter) {
        this.descriptionFilter = descriptionFilter;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getSelectedProduto() {
        return selectedProduto;
    }

    public void setSelectedProduto(Produto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }
}

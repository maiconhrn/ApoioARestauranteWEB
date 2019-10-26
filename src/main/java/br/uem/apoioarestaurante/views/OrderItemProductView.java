package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.controllers.OrderItemProductController;
import br.uem.apoioarestaurante.metadata.entities.Product;
import br.uem.apoioarestaurante.models.OrderModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maicon
 */
@Named
@ViewScoped
public class OrderItemProductView implements Serializable {

    @Inject
    private OrderItemProductController orderItemProductController;

    @Inject
    private OrderModel orderModel;

    private boolean idFilterSelected;
    private boolean descriptionFilterSelected;
    private String idFilter;
    private String descriptionFilter;
    private List<Product> products = new ArrayList<>();
    private Product selectedProduct;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}

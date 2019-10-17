package br.uem.apoioarestaurante.metadata.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Maicon
 */
@Entity
@Table(name = "aar_order_item")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "products_qtt")
    private Integer productQtt;

    @Column(name = "price")
    private Double price;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "order_id")
    private Order order;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @Column(name = "active")
    private Boolean active;

    public OrderItem() {
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public Integer getProductQtt() {
        return productQtt;
    }

    public void setProductQtt(Integer productQtt) {
        this.productQtt = productQtt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

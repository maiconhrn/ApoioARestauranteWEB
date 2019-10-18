package br.uem.apoioarestaurante.metadata.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "aar_user")
public class User extends Person implements Serializable {

    private static final long serialVersionUID = -312099716113507516L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User() {
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
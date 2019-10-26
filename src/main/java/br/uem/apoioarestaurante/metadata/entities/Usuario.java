package br.uem.apoioarestaurante.metadata.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "aar_usuario")
public class Usuario extends Pessoa implements Serializable {

    private static final long serialVersionUID = -312099716113507516L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true, nullable = false, name = "login")
    private String login;

    @Column(nullable = false, name = "senha")
    private String senha;

    @Column(name = "ativo")
    private Boolean ativo;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    public Usuario() {
        this.ativo = true;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
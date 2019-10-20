package br.uem.apoioarestaurante.models;

import br.uem.apoioarestaurante.metadata.entities.Person;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuarios extends Person {

    
    private static final long serialVersionUID = -312099716113507516L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(name = "Ativo")
    private Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
}
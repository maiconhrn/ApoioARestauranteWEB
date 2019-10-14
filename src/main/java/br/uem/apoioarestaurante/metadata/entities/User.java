package br.uem.apoioarestaurante.metadata.entities;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends Person implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

    @Override
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
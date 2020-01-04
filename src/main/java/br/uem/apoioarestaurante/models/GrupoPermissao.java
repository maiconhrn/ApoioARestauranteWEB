package br.uem.apoioarestaurante.models;

/**
 * Universidade Estadual de Maringá
 * <p>
 * Autor: José Gabriel Júnior       Ra: 54011
 */

import br.uem.apoioarestaurante.metadata.entities.Usuario;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class GrupoPermissao implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column
    private String descricao;

    @Column
    private String observacao;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "grupopermissao_permissao",
            joinColumns = @JoinColumn(name = "grupopermissao_ID"),
            inverseJoinColumns = @JoinColumn(name = "permissao_ID"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Permissao> permissoes;

    @OneToMany(mappedBy = "grupo")
    private List<Usuario> usuarios;

    public GrupoPermissao() {
        this.permissoes = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
package br.uem.apoioarestaurante.models;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class GrupoPermissao implements Serializable{

    private static final long serialVersionUID = 1L;
       
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column
    private String descricao;
    
    @Column
    private String observacao;
    
    @ManyToMany (fetch = FetchType.EAGER ,cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable( name="grupopermissao_permissao",
            joinColumns=@JoinColumn(name="grupopermissao_ID"),
            inverseJoinColumns=@JoinColumn(name="permissao_ID"))
    private List<Permissao> permissoes;

    public GrupoPermissao(Long id, String descricao, String observacao, List<Permissao> permissoes) {
        this.id = id;
        this.descricao = descricao;
        this.observacao = observacao;
        this.permissoes = permissoes;
    }

    public GrupoPermissao() {
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

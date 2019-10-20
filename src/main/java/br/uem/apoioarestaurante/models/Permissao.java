package br.uem.apoioarestaurante.models;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */

import java.util.Objects;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Permissao")
@Inheritance(strategy=InheritanceType.JOINED)
public class Permissao implements Serializable{

    private static final long serialVersionUID = 1L;
       
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column
    private String descricao;
    
    @Column
    private String observacao;
    

    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Grupopermissao.class)
    @JoinTable( name="permissao_grupopermissao", joinColumns=@JoinColumn(name="permissao_ID"), inverseJoinColumns=@JoinColumn(name="Grupopermissao_ID"))
    private List<Grupopermissao> grupo;

    public Permissao(Long id, String descricao, String observacao, List<Grupopermissao> grupo) {
        this.id = id;
        this.descricao = descricao;
        this.observacao = observacao;
        this.grupo = grupo;
    }

    public Permissao() {
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

    public List<Grupopermissao> getGrupo() {
        return grupo;
    }

    public void setGrupo(List<Grupopermissao> grupo) {
        this.grupo = grupo;
    }

}

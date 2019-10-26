package br.uem.apoioarestaurante.metadata.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Maicon
 */
@Entity
@Table(name = "aar_produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = -943067544577531821L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(name = "fornecedor")
    private String fornecedor;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "preco_venda")
    private Double precoVenda;

    @Column(name = "ativo")
    private Boolean ativo;

    public Produto() {
        this.dataCadastro = new Date();
        this.ativo = true;
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}

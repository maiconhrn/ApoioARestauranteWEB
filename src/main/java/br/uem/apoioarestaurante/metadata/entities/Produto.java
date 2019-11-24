package br.uem.apoioarestaurante.metadata.entities;

import br.uem.apoioarestaurante.metadata.types.ProdutoTipo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
    private ProdutoTipo tipo;

    @Column(name = "preco_venda")
    private Double precoVenda;
    
    @Column(name = "preco_compra")
    private Double precoCompra;

    @Column(name = "ativo")
    private Boolean ativo;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;
    
    @Column(name = "unidade_medida")
    private String unidadeMedida; 

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

    public ProdutoTipo getTipo() {
        return tipo;
    }

    public void setTipo(ProdutoTipo tipo) {
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

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }   
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(descricao, produto.descricao) &&
                Objects.equals(dataCadastro, produto.dataCadastro) &&
                Objects.equals(fornecedor, produto.fornecedor) &&
                tipo == produto.tipo &&
                Objects.equals(precoVenda, produto.precoVenda) &&
                Objects.equals(ativo, produto.ativo) &&
                Objects.equals(estoque, produto.estoque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, dataCadastro, fornecedor, tipo, precoVenda, ativo, estoque);
    }
}

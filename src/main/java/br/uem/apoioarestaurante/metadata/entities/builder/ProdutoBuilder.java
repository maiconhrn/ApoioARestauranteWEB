package br.uem.apoioarestaurante.metadata.entities.builder;

import br.uem.apoioarestaurante.metadata.entities.Estoque;
import br.uem.apoioarestaurante.metadata.entities.Produto;
import br.uem.apoioarestaurante.metadata.types.ProdutoTipo;

import java.util.Date;

/**
 * @author Maicon
 */
public final class ProdutoBuilder {
    private String descricao;
    private Date dataCadastro;
    private String fornecedor;
    private ProdutoTipo tipo;
    private Double precoVenda;
    private Double precoCompra;
    private Boolean ativo;
    private Estoque estoque;
    private String unidadeMedida;

    private ProdutoBuilder() {
    }

    public static ProdutoBuilder aProduto() {
        return new ProdutoBuilder();
    }

    public ProdutoBuilder withDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ProdutoBuilder withDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public ProdutoBuilder withFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
        return this;
    }

    public ProdutoBuilder withTipo(ProdutoTipo tipo) {
        this.tipo = tipo;
        return this;
    }

    public ProdutoBuilder withPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
        return this;
    }

    public ProdutoBuilder withPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
        return this;
    }

    public ProdutoBuilder withAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public ProdutoBuilder withEstoque(Estoque estoque) {
        this.estoque = estoque;
        return this;
    }

    public ProdutoBuilder withUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
        return this;
    }

    public Produto build() {
        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produto.setDataCadastro(dataCadastro);
        produto.setFornecedor(fornecedor);
        produto.setTipo(tipo);
        produto.setPrecoVenda(precoVenda);
        produto.setPrecoCompra(precoCompra);
        produto.setAtivo(ativo);
        produto.setEstoque(estoque);
        produto.setUnidadeMedida(unidadeMedida);
        return produto;
    }
}

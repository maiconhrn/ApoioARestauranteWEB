package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.dao.ProdutoDAO;
import br.uem.apoioarestaurante.metadata.entities.Produto;
import br.uem.apoioarestaurante.metadata.types.ProdutoTipo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

@Named
@SessionScoped
public class ManterProdutoController implements Serializable {

    private Produto       produtoSelecionado = new Produto();//USADO NA ALTERACAO E EXCLUSAO
    private Produto       produto            = new Produto();
    private List<Produto> produtos           = new ArrayList<>();
    private ProdutoDAO    prodao;
    private String        consoleTipo;
    private String        descricaoPesquisa;
    private Long          IDPesquisa;

    public ManterProdutoController() {
        produto  = new Produto();
        prodao   = new ProdutoDAO();
        produtos = null;
        this.setConsoleTipo( null );
        prodao.connect();
        produtos = prodao.listAll();
        prodao.disconnect();
    }

    public void pesquisar() throws SQLException, ClassNotFoundException {
        List<Produto> produtosPesquisa = new ArrayList<>();
        this.listarProdutos();

        if ( descricaoPesquisa.length() == 0 ) {
            return;
        }

        for ( int i = 0; i < this.produtos.size(); i++ ) {
            if ( produtos.get(i).getDescricao().indexOf(this.descricaoPesquisa.toUpperCase()) >= 0 ) {
                produtosPesquisa.add(produtos.get(i));
            }
        }

        this.produtos = produtosPesquisa;
    }

    public boolean verificarExistenciaID(Long ID) {

        for (int i = 0; i < this.produtos.size(); i++) {
            if (Objects.equals(this.produtos.get(i).getId(), ID)) {
                return true;
            }
        }

        return false;
    }

    public void buscarProduto() throws SQLException {
        this.listarProdutos();
        if ( this.IDPesquisa == null || this.IDPesquisa == 0 ) {            
            this.setConsoleTipo( null );
            this.produtoSelecionado = new Produto();            
        }
        else{
            for (int i = 0; i < this.produtos.size(); i++) {
                if (Objects.equals(this.produtos.get(i).getId(), this.IDPesquisa)) {
                    this.produtoSelecionado = this.produtos.get(i);
                    this.consoleTipo = this.produtos.get(i).getTipo().getTextValue();               
                    return;
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "ID não encontrado."));
        this.setConsoleTipo(null);
        this.produtoSelecionado = new Produto();
    }

    public void inserirProduto(Produto novoProduto) throws SQLException, ClassNotFoundException {
        ManterEstoqueController manterEstoque = new ManterEstoqueController();
        if (null == this.getConsoleTipo()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Selecione o Tipo."));
            return;
        } else {
            switch (this.getConsoleTipo()) {
                case "Manufaturado":
                    novoProduto.setTipo(ProdutoTipo.MANUFACTURED);
                    break;
                case "Revenda":
                    novoProduto.setTipo(ProdutoTipo.RESALE);
                    break;
                default:
                    novoProduto.setTipo(ProdutoTipo.FEEDSTOCK);
                    break;
            }
        }
        this.produtoToUpper(novoProduto);
        prodao.connect();
        prodao.save(novoProduto);
        prodao.disconnect();
        manterEstoque.getEstoque().setProduto(novoProduto);
        manterEstoque.getEstoque().setAtivo(Boolean.TRUE);
        manterEstoque.getEstoque().setPedidoFeito(Boolean.TRUE);
        manterEstoque.getEstoque().setQtdEmEstoque(0);
        manterEstoque.getEstoque().setQtdMinima(0);
        manterEstoque.salvarEstoque(manterEstoque.getEstoque());        
        this.listarProdutos();
        this.setConsoleTipo(null);
        produto = new Produto();

    }

    private void produtoToUpper(Produto produto) {
        produto.setDescricao(produto.getDescricao().toUpperCase());
        produto.setFornecedor(produto.getFornecedor().toUpperCase());
        produto.setUnidadeMedida(produto.getUnidadeMedida().toUpperCase());
    }

    public void alterarProduto(Produto produ) throws ClassNotFoundException, SQLException {

        this.listarProdutos();

        if (null == this.getConsoleTipo() || produ == null || !verificarExistenciaID( produ.getId() )) {
            if ( null == this.getConsoleTipo()) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Selecione o tipo."));
            if( !verificarExistenciaID( produ.getId() )) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Pesquise um produto válido antes."));
            return;
        } else {
            switch (this.getConsoleTipo()) {
                case "Manufaturado":
                    produ.setTipo(ProdutoTipo.MANUFACTURED);
                    break;
                case "Revenda":
                    produ.setTipo(ProdutoTipo.RESALE);
                    break;
                default:
                    produ.setTipo(ProdutoTipo.FEEDSTOCK);
                    break;
            }
        }
        this.produtoToUpper(produ);
        prodao.connect();
        prodao.update(produ);
        prodao.disconnect();
        this.listarProdutos();
        this.setConsoleTipo(null);
        this.produtoSelecionado = new Produto();
        this.IDPesquisa = null;
    }

    public void excluirProduto(Produto produ) throws SQLException {
        this.listarProdutos();

        if ((produ == null) || !verificarExistenciaID(produ.getId())) {
            return;
        }

        prodao.connect();
        prodao.delete(produ);
        prodao.disconnect();
        this.listarProdutos();
        produto = new Produto();
    }

    public void ativarDesativarProduto(Produto produto) throws ClassNotFoundException, SQLException {
        if (produto == null || !verificarExistenciaID( produto.getId()) ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Produto inválido."));
            return;
        }

        if (produto.getAtivo()) {
            produto.setAtivo(Boolean.FALSE);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Produto: " + String.valueOf(produto.getId()) + " foi desativado!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Produto: " + String.valueOf(produto.getId()) + " foi ativado!"));
            produto.setAtivo(Boolean.TRUE);
        }

        alterarProduto(produto);
        this.setConsoleTipo(null);
        this.produtoSelecionado = new Produto();
    }

    public void listarProdutos() throws SQLException {
        produtos = null;
        prodao.connect();
        produtos = prodao.listAll();
        prodao.disconnect();
    }

    public void limparCampos() {
        produto = new Produto();
    }

    public String getConsoleTipo() {
        return consoleTipo;
    }

    public void setConsoleTipo(String console) {
        this.consoleTipo = console;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getDescricaoPesquisa() {
        return descricaoPesquisa;
    }

    public void setDescricaoPesquisa(String descricaoPesquisa) {
        this.descricaoPesquisa = descricaoPesquisa;
    }

    public Long getIDPesquisa() {
        return IDPesquisa;
    }

    public void setIDPesquisa(Long IDPesquisa) {
        this.IDPesquisa = IDPesquisa;
    }

}

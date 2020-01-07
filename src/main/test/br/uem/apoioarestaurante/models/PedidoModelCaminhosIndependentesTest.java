package br.uem.apoioarestaurante.models;

import br.uem.apoioarestaurante.dao.ItemPedidoDAO;
import br.uem.apoioarestaurante.metadata.entities.*;
import br.uem.apoioarestaurante.metadata.entities.builder.ItemPedidoBuilder;
import br.uem.apoioarestaurante.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Collections;

import static br.uem.apoioarestaurante.utils.TestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Maicon
 */
public class PedidoModelCaminhosIndependentesTest {
    private PedidoModel model;
    private Permissao permissao;
    private GrupoPermissao grupoPermissao;
    private Usuario usuario;
    private Cliente cliente;
    private ItemPedido itemPedido;
    private Estoque estoque;
    private Produto produto;
    private Pedido pedido;

    @Before
    public void setUp() throws ParseException {
        model = new PedidoModel();
        permissao = createPermissao();
        grupoPermissao = createGrupoPermissao(Collections.singletonList(permissao));
        usuario = createUser(grupoPermissao);
        cliente = createCliente();
        produto = createProduto(estoque);
        estoque = createEstoque(produto, 100, 50);
        pedido = createPedididoDelivery(usuario, cliente, Collections.singletonList(itemPedido));
        itemPedido = createItemPedido(pedido, produto, 3);
    }

    @After
    public void clear() {
        HibernateUtil.closeSessionFactory();
    }

    /*
     * Inicio Testes por caminhos independentes
     */
    @Test
    public void updateItemPedidoCaso1() throws Exception {
        Produto produtoSemEstoque = createProduto();
        ItemPedido itemPedidoSemEstoque = createItemPedido(pedido, produtoSemEstoque, 2);
        model.update(itemPedidoSemEstoque);
        assertNull(produtoSemEstoque.getEstoque());
        assertNull(itemPedidoSemEstoque.getProduto().getEstoque());
        assertEquals(100, (int) estoque.getQtdEmEstoque());
    }

    @Test
    public void updateItemPedidoCaso2() throws Exception {
        itemPedido.setAtivo(false);
        model.update(itemPedido);
        assertEquals(103, (int) estoque.getQtdEmEstoque());
    }

    @Test
    public void updateItemPedidoCaso3() throws Exception {
        ItemPedido itemPedidoNaoSalvo = ItemPedidoBuilder.anItemPedido()
                .withAtivo(true)
                .withPedido(pedido)
                .withProduto(produto)
                .withQtdProduto(5)
                .withPreco(produto.getPrecoVenda() * 5)
                .build();

        model.update(itemPedidoNaoSalvo);
        assertEquals(95, (int) estoque.getQtdEmEstoque());
    }

    @Test
    public void updateItemPedidoCaso4() throws Exception {
        ItemPedidoDAO dao = ItemPedidoDAO.getInstance();

        dao.connect();
        dao.delete(itemPedido);
        dao.disconnect();

        itemPedido.setQtdProduto(2);
        model.update(itemPedido);
        assertEquals(100, (int) estoque.getQtdEmEstoque());
    }

    @Test
    public void updateItemPedidoCaso5() throws Exception {
        model.update(itemPedido);
        assertEquals(100, (int) estoque.getQtdEmEstoque());
    }

    @Test
    public void updateItemPedidoCaso6() throws Exception {
        itemPedido.setQtdProduto(5);
        model.update(itemPedido);
        assertEquals(98, (int) estoque.getQtdEmEstoque());

        itemPedido.setQtdProduto(1);
        model.update(itemPedido);
        assertEquals(102, (int) estoque.getQtdEmEstoque());
    }
    /*
     * Fim Testes por caminhos independentes
     */
}
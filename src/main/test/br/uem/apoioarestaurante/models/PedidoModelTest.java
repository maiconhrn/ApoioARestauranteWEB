package br.uem.apoioarestaurante.models;

import br.uem.apoioarestaurante.metadata.entities.*;
import br.uem.apoioarestaurante.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import static br.uem.apoioarestaurante.utils.TestUtil.*;
import static org.junit.Assert.*;

/**
 * @author Maicon
 */
public class PedidoModelTest {
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

    @Test
    public void update() throws Exception {
        assertEquals(100, (int) estoque.getQtdEmEstoque());

        itemPedido.setQtdProduto(1);
        model.update(itemPedido);
        assertEquals(102, (int) estoque.getQtdEmEstoque());

        itemPedido.setQtdProduto(5);
        model.update(itemPedido);
        assertEquals(98, (int) estoque.getQtdEmEstoque());

        itemPedido.setAtivo(false);
        model.update(itemPedido);
        assertEquals(103, (int) estoque.getQtdEmEstoque());

        try {
            model.update((ItemPedido) null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void saveOrUpdate() {
        assertNull(pedido.getTotal());

        Long pedidoID = pedido.getId();
        AtomicReference<Double> totalPrice = new AtomicReference<>(0D);
        pedido.getItems().forEach(orderItem -> totalPrice.updateAndGet(v -> v + orderItem.getPreco()));
        pedido.setTotal(totalPrice.get());
        model.saveOrUpdate(pedido);
        assertEquals(pedidoID, pedido.getId());
        assertEquals(totalPrice.get(), pedido.getTotal());
    }
}
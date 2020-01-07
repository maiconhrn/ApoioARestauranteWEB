package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.metadata.entities.*;
import br.uem.apoioarestaurante.models.GrupoPermissao;
import br.uem.apoioarestaurante.models.Permissao;
import br.uem.apoioarestaurante.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Collections;

import static br.uem.apoioarestaurante.utils.TestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Maicon
 */
public class ItemPedidoDAOTest {

    private ItemPedidoDAO itemPedidoDAO;
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
        itemPedidoDAO = ItemPedidoDAO.getInstance();
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
    public void findById() {
        itemPedidoDAO.connect();
        ItemPedido itemPedido = itemPedidoDAO.findById(1L);
        itemPedidoDAO.disconnect();

        assertNotNull(itemPedido);
        assertEquals(1L, (long) itemPedido.getId());
    }
}
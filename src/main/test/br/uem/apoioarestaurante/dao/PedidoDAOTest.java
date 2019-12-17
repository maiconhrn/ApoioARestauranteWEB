package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.metadata.entities.*;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;
import br.uem.apoioarestaurante.metadata.types.ProdutoTipo;
import br.uem.apoioarestaurante.models.GrupoPermissao;
import org.junit.Test;
import br.uem.apoioarestaurante.models.Permissao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertNotNull;

/**
 * @author Maicon
 */
public class PedidoDAOTest {

    @Test
    public void seachByFilters() throws ParseException {
        PedidoDAO pedidoDAO = PedidoDAO.getInstance();
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
        EstoqueDAO estoqueDAO = EstoqueDAO.getInstance();
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        
        Cliente cliente = new Cliente();
        cliente.setNome("Agostinho Carrara");
        cliente.setCpf("12365484452");
        cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("12/01/1971"));
        cliente.setCidade("Maringá");
        cliente.setEstado("Paraná");
        cliente.setBairro("Barrão");
        cliente.setNumero(24);
        cliente.setTelefone("4412345678");

        clienteDAO.connect();
        clienteDAO.save(cliente);
        clienteDAO.disconnect();

        Estoque estoque1 = new Estoque();
        Produto produto1 = new Produto();
        produto1.setDescricao("Coca cola 600ml");
        produto1.setTipo(ProdutoTipo.RESALE);
        produto1.setPrecoVenda(4.5D);
        produto1.setFornecedor("Coca Cola Brasil");
        produto1.setEstoque(estoque1);

        estoque1.setProduto(produto1);
        estoque1.setQtdMinima(50);
        estoque1.setQtdEmEstoque(100);

        Produto produto2 = new Produto();
        produto2.setDescricao("Baião de 2 250g");
        produto2.setTipo(ProdutoTipo.MANUFACTURED);
        produto2.setPrecoVenda(35D);
        produto2.setFornecedor("Fabricação própria");

        produtoDAO.connect();
        produtoDAO.save(produto1);
        produtoDAO.save(produto2);
        produtoDAO.disconnect();

        estoqueDAO.connect();
        estoqueDAO.save(estoque1);
        estoqueDAO.disconnect();

        ItemPedido item1 = new ItemPedido();
        item1.setProduto(produto1);
        item1.setQtdProduto(2);
        item1.setPreco(produto1.getPrecoVenda() * item1.getQtdProduto());

        ItemPedido item2 = new ItemPedido();
        item2.setProduto(produto1);
        item2.setQtdProduto(4);
        item2.setPreco(produto1.getPrecoVenda() * item2.getQtdProduto());

        ItemPedido item3 = new ItemPedido();
        item3.setProduto(produto2);
        item3.setQtdProduto(4);
        item3.setPreco(produto2.getPrecoVenda() * item3.getQtdProduto());

        Pedido pedido1 = new Pedido();
        pedido1.setDataInicio(new Date());
        pedido1.setTipo(PedidoTipo.LOCAL);
        pedido1.setUsuario(usuarioDAO.findById(Long.MIN_VALUE));
        pedido1.setMesa(23);
        pedido1.setItems(Arrays.asList(item1));
        item1.setPedido(pedido1);
        AtomicReference<Double> totalPrice1 = new AtomicReference<>(0D);
        pedido1.getItems().forEach(orderItem -> totalPrice1.updateAndGet(v -> v + orderItem.getPreco()));
        pedido1.setTotal(totalPrice1.get());

        Pedido pedido2 = new Pedido();
        pedido2.setDataInicio(new Date());
        pedido2.setTipo(PedidoTipo.DELIVERY);
        pedido2.setUsuario(usuarioDAO.findById(Long.MIN_VALUE));
        pedido2.setCliente(cliente);
        pedido2.setItems(Arrays.asList(item2, item3));
        item2.setPedido(pedido2);
        item3.setPedido(pedido2);
        AtomicReference<Double> totalPrice2 = new AtomicReference<>(0D);
        pedido2.getItems().forEach(orderItem -> totalPrice2.updateAndGet(v -> v + orderItem.getPreco()));
        pedido2.setTotal(totalPrice2.get());
        pedido2.setDataFim(new Date());

        pedidoDAO.connect();
        pedidoDAO.save(pedido1);
        pedidoDAO.disconnect();

        pedidoDAO.connect();
        pedidoDAO.save(pedido2);
        pedidoDAO.disconnect();

        pedidoDAO.connect();
        List<Pedido> pedidos = pedidoDAO.findOrdersByFilters(PedidoTipo.BOTH, true, 1L, false, 0, false, 0, false, 0);
        pedidoDAO.disconnect();
        assertNotNull(pedidos);
    }
}
package br.uem.apoioarestaurante.utils;

import br.uem.apoioarestaurante.dao.*;
import br.uem.apoioarestaurante.dao.generic.intf.BasicGenericDAO;
import br.uem.apoioarestaurante.metadata.entities.*;
import br.uem.apoioarestaurante.metadata.entities.builder.*;
import br.uem.apoioarestaurante.metadata.types.PedidoStatusTipo;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;
import br.uem.apoioarestaurante.metadata.types.ProdutoTipo;
import br.uem.apoioarestaurante.models.GrupoPermissao;
import br.uem.apoioarestaurante.models.Permissao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public class TestUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static BasicGenericDAO dao;

    private static Object saveEntity(Object o, BasicGenericDAO dao) {
        dao.connect();
        dao.save(o);
        dao.disconnect();

        return o;
    }

    public static Permissao createPermissao() {
        return (Permissao) saveEntity(PermissaoBuilder.aPermissao()
                .withDescricao("ADM")
                .withObservacao("Permissão para adiministrador")
                .build(), PermissaoDAO.getInstance());
    }

    public static GrupoPermissao createGrupoPermissao(List<Permissao> permissoes) {
        return (GrupoPermissao) saveEntity(GrupoPermissaoBuilder.aGrupoPermissao()
                .withDescricao("ADM")
                .withObservacao("Grupo de permissões para adiministrador")
                .withPermissoes(permissoes)
                .build(), GrupoPermissaoDAO.getInstance());
    }

    public static Usuario createUser(GrupoPermissao grupoPermissao) throws ParseException {
        return (Usuario) saveEntity(UsuarioBuilder.anUsuario()
                .withAtivo(true)
                .withCpf("748.247.830-20")
                .withDataNascimento(sdf.parse("22/01/1975"))
                .withNome("Antonio José")
                .withLogin("ajose")
                .withSenha("antonio123")
                .withGrupo(grupoPermissao)
                .build(), UsuarioDAO.getInstance());
    }

    public static Cliente createCliente() throws ParseException {
        return (Cliente) saveEntity(ClienteBuilder.aCliente()
                .withAtivo(true).withCpf("237.488.690-53")
                .withDataNascimento(sdf.parse("12/05/1971"))
                .withNome("Marcos Domingues")
                .withBairro("Bairro Neighborhood")
                .withCidade("Maringá")
                .withEstado("Paraná")
                .withComplemento("Casa A")
                .withNumero(2424)
                .withRua("Rua Street")
                .withTelefone("4433223322")
                .build(), ClienteDAO.getInstance());
    }

    public static Estoque createEstoque(Produto produto, Integer qtdEmEstoque, Integer qtdMinima) {
        Date now = new Date();


        Estoque estoque = (Estoque) saveEntity(EstoqueBuilder.anEstoque()
                .withAtivo(true)
                .withProduto(produto)
                .withQtdEmEstoque(qtdEmEstoque)
                .withQtdMinima(qtdMinima)
                .withUltimaEntrada(now)
                .withUltimaSaida(now)
                .build(), EstoqueDAO.getInstance());

        produto.setEstoque(estoque);

        dao = ProdutoDAO.getInstance();
        dao.connect();
        dao.update(produto);
        dao.disconnect();

        return estoque;
    }

    public static Produto createProduto() {
        return (Produto) saveEntity(ProdutoBuilder.aProduto()
                .withAtivo(true)
                .withDataCadastro(new Date())
                .withDescricao("Escondidinho de frango 300g")
                .withPrecoVenda(25D)
                .withTipo(ProdutoTipo.MANUFACTURED)
                .withUnidadeMedida("Unidade")
                .build(), ProdutoDAO.getInstance());
    }

    public static Produto createProduto(Estoque estoque) {
        return (Produto) saveEntity(ProdutoBuilder.aProduto()
                .withAtivo(true)
                .withDataCadastro(new Date())
                .withDescricao("Coca cola 600ml")
                .withEstoque(estoque)
                .withFornecedor("Coca Cola Brasil")
                .withPrecoCompra(2.5D)
                .withPrecoVenda(4.5D)
                .withTipo(ProdutoTipo.RESALE)
                .withUnidadeMedida("Unidade")
                .build(), ProdutoDAO.getInstance());
    }

    public static ItemPedido createItemPedido(Pedido pedido, Produto produto, Integer qtdProduto) {
        ItemPedido itemPedido = (ItemPedido) saveEntity(ItemPedidoBuilder.anItemPedido()
                .withAtivo(true)
                .withPedido(pedido)
                .withProduto(produto)
                .withQtdProduto(qtdProduto)
                .withPreco(produto.getPrecoVenda() * qtdProduto)
                .build(), ItemPedidoDAO.getInstance());

        pedido.setItems(Collections.singletonList(itemPedido));
        dao = PedidoDAO.getInstance();
        dao.connect();
        dao.update(pedido);
        dao.disconnect();

        return itemPedido;
    }

    public static Pedido createPedididoDelivery(Usuario usuario, Cliente cliente, List<ItemPedido> itens) {
        return (Pedido) saveEntity(PedidoBuilder.aPedido()
                .withAtivo(true)
                .withDataInicio(new Date())
                .withStatus(PedidoStatusTipo.OPENED)
                .withTipo(PedidoTipo.DELIVERY)
                .withUsuario(usuario)
                .withCliente(cliente)
                .withItems(itens)
                .withTotal(0D)
                .build(), PedidoDAO.getInstance());
    }
}

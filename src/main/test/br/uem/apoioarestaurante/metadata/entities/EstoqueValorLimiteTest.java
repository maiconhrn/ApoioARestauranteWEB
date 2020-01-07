package br.uem.apoioarestaurante.metadata.entities;

import br.uem.apoioarestaurante.exceptions.EstoqueException;
import br.uem.apoioarestaurante.metadata.types.MovimentoEstoqueTipo;
import br.uem.apoioarestaurante.models.GrupoPermissao;
import br.uem.apoioarestaurante.models.Permissao;
import br.uem.apoioarestaurante.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Collections;

import static br.uem.apoioarestaurante.utils.TestUtil.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Maicon
 */
public class EstoqueValorLimiteTest {

    private Usuario usuario;
    private Permissao permissao;
    private GrupoPermissao grupoPermissao;
    private Estoque estoque;
    private Produto produto;

    @Before
    public void setUp() throws ParseException {
        usuario = createUser(grupoPermissao);
        permissao = createPermissao();
        grupoPermissao = createGrupoPermissao(Collections.singletonList(permissao));
        produto = createProduto(estoque);
        estoque = createEstoque(produto, 100, 50);
    }

    @After
    public void clear() {
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void novaMovimentacaoEstoqueCaso1() {
        try {
            MovimentoEstoque movimentoEstoque = estoque.novaMovimentacaoEstoque(MovimentoEstoqueTipo.IN, 0, usuario);
        } catch (EstoqueException e) {
            assertTrue(e.getMessage().contains("Não e possivel fazer baixa no estoque: quantidade para movimentar é invalida"));
            return;
        }

        assertTrue(false);
    }

    @Test
    public void novaMovimentacaoEstoqueCaso2() {
        try {
            MovimentoEstoque movimentoEstoque = estoque.novaMovimentacaoEstoque(MovimentoEstoqueTipo.IN, 1, usuario);
            assertNotNull(movimentoEstoque);
        } catch (EstoqueException e) {
            assertTrue(false);
        }
    }

    @Test
    public void novaMovimentacaoEstoqueCaso3() {
        try {
            MovimentoEstoque movimentoEstoque = estoque.novaMovimentacaoEstoque(MovimentoEstoqueTipo.IN, 2, usuario);
            assertNotNull(movimentoEstoque);
        } catch (EstoqueException e) {
            assertTrue(false);
        }
    }

    @Test
    public void novaMovimentacaoEstoqueCaso4() {
        try {
            estoque.setQtdMinima(1);
            estoque.setQtdEmEstoque(4);
            MovimentoEstoque movimentoEstoque = estoque.novaMovimentacaoEstoque(MovimentoEstoqueTipo.OUT, 5, usuario);
        } catch (EstoqueException e) {
            assertTrue(e.getMessage().contains("Não e possivel fazer baixa no estoque: quantidade em estoque não suportada"));
            return;
        }

        assertTrue(false);
    }

    @Test
    public void novaMovimentacaoEstoqueCaso5() {
        try {
            estoque.setQtdMinima(1);
            estoque.setQtdEmEstoque(4);
            MovimentoEstoque movimentoEstoque = estoque.novaMovimentacaoEstoque(MovimentoEstoqueTipo.OUT, 4, usuario);
        } catch (EstoqueException e) {
            assertTrue(e.getMessage().contains("Não e possivel fazer baixa no estoque: quantidade em estoque não suportada"));
            return;
        }

        assertTrue(false);
    }

    @Test
    public void novaMovimentacaoEstoqueCaso6() {
        try {
            estoque.setQtdMinima(1);
            estoque.setQtdEmEstoque(4);
            MovimentoEstoque movimentoEstoque = estoque.novaMovimentacaoEstoque(MovimentoEstoqueTipo.OUT, 3, usuario);
            assertNotNull(movimentoEstoque);
        } catch (EstoqueException e) {
            assertTrue(false);
        }
    }
}
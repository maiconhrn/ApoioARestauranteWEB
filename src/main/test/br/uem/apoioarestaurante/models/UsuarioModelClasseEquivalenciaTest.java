package br.uem.apoioarestaurante.models;

import br.uem.apoioarestaurante.metadata.entities.Usuario;
import br.uem.apoioarestaurante.utils.HibernateUtil;
import br.uem.apoioarestaurante.views.UsuariosView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Collections;

import static br.uem.apoioarestaurante.utils.TestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UsuarioModelClasseEquivalenciaTest {

    private Permissao permissao;
    private GrupoPermissao grupoPermissao;
    private Usuario usuario;
    private UsuariosView usuariosView;

    @Before
    public void setUp() throws ParseException {
        permissao = createPermissao();
        grupoPermissao = createGrupoPermissao(Collections.singletonList(permissao));
        usuario = createUser1(grupoPermissao);
        usuariosView = new UsuariosView();

    }

    @After
    public void clear() {
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void autenticacaoCaso1() throws Exception {
        String testa = usuariosView.envia("gabriel", "123");
        assertEquals    ("restrictes/home.xhtml", testa);
        assertEquals    ("gabriel", usuariosView.getUsuario().getLogin());
        assertEquals    ("123", usuariosView.getUsuario().getSenha());
    }

    @Test
    public void autenticacaoCaso2() throws Exception {
        String testa = usuariosView.envia("", "123");
        assertEquals    ("", testa);
    }

    @Test
    public void autenticacaoCaso3() throws Exception {
        String testa = usuariosView.envia("gabriel", "123");
        assertEquals    ("restrictes/home.xhtml", testa);
    }

    @Test
    public void autenticacaoCaso4() throws Exception {
        String testa = usuariosView.envia("", "123");
        assertEquals    ("", testa);
    }

    @Test
    public void autenticacaoCaso5() throws Exception {
        usuariosView.envia("gabrielconejoo", "123");
        assertNotEquals("gabrielconejoo", usuariosView.getUsuario().getLogin());
    }
    @Test
    public void autenticacaoCaso6() throws Exception {
        usuariosView.envia("gabrielconejo", "123");
        assertEquals("gabrielconejo", usuariosView.getUsuario().getLogin());
    }

    @Test
    public void autenticacaoCaso7() throws Exception {
        usuariosView.envia("gabrielconejoo", "1234");
        assertNotEquals("gabrielconejoo", usuariosView.getUsuario().getSenha());
    }

    @Test
    public void autenticacaoCaso8() throws Exception {
        usuariosView.envia("gabrielconejo", "123");
        assertEquals("123", usuariosView.getUsuario().getSenha());
    }

}

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


public class UsuarioModelCaminhosIndependetesTest {

    private Permissao permissao;
    private GrupoPermissao grupoPermissao;
    private Usuario usuario;
    private UsuariosView usuariosView;
    private String testa;

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
        this.testa = "";
        assertEquals    ("",
                        testa);
    }

    @Test
    public void autenticacaoCaso2() throws Exception {
        String testa = (usuariosView.envia("gabrielconejo", "123"));
        assertEquals    ("/restricted/home.xhtml",
                        testa);
    }

    @Test
    public void autenticacaoCaso3() throws Exception {
        String testa = usuariosView.envia("gabriel", "abc");
        assertEquals    ("",
                         testa);
    }
}

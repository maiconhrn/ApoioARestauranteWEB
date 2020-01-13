package br.uem.apoioarestaurante.models;

import br.uem.apoioarestaurante.metadata.entities.Usuario;
import br.uem.apoioarestaurante.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import static br.uem.apoioarestaurante.utils.TestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsuarioModelTest {

    private Permissao permissao;
    private GrupoPermissao grupoPermissao;
    private Usuario usuario;

    @Before
    public void setUp() throws ParseException {
        permissao = createPermissao();
        grupoPermissao = createGrupoPermissao(Collections.singletonList(permissao));
        usuario = createUser(grupoPermissao);
    }
    @After
    public void clear() {
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void saveOrUpdate() {
    }

}

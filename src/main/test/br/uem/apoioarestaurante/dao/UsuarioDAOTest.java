package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.metadata.entities.Usuario;
import br.uem.apoioarestaurante.models.GrupoPermissao;
import br.uem.apoioarestaurante.models.Permissao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author GabrielConejo
 */
public class UsuarioDAOTest {
    @Test
    public void seachByFilters() throws ParseException {
    
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        PermissaoDAO permissaoDAO = PermissaoDAO.getInstance();
        GrupoPermissaoDAO grupoPermissaoDAO = GrupoPermissaoDAO.getInstance();
        
        Usuario usuario = new Usuario();
        Usuario usuario2 = new Usuario();
        Usuario usuario3 = new Usuario();
        Usuario usuario4 = new Usuario();
        Permissao permissao = new Permissao();
        
        permissao.setDescricao("ADM");
        permissao.setObservacao("Permissão para adiministrador");
        
        GrupoPermissao grupo = new GrupoPermissao();
        grupo.setDescricao("ADM");
        grupo.setObservacao("Grupo de permissões para adiministrador");
        grupo.setPermissoes((List<Permissao>) permissao);
        grupo.setUsuarios((List<Usuario>) usuario);
        grupo.setUsuarios((List<Usuario>) usuario2);
        grupo.setUsuarios((List<Usuario>) usuario3);
        grupo.setUsuarios((List<Usuario>) usuario4);
        
        
        usuario.setNome("José Gabriel");
        usuario.setCpf("10400000000");
        usuario.setLogin("jose");
        usuario.setSenha("123");
        usuario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1990"));
        usuario.setGrupo(grupo);
        usuario.setAtivo(Boolean.TRUE);

        usuarioDAO.connect();
        usuarioDAO.save(usuario);
        usuarioDAO.disconnect();
        
        
        usuario.setNome("Gabriel de Oliveira Conejo");
        usuario.setCpf("10230804942");
        usuario.setLogin("gabriel");
        usuario.setSenha("123");
        usuario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("06/04/1999"));
        usuario.setGrupo(grupo);
        usuario.setAtivo(Boolean.TRUE);

        usuarioDAO.connect();
        usuarioDAO.save(usuario2);
        usuarioDAO.disconnect();
        
        
        usuario.setNome("Maicon Hesrique");
        usuario.setCpf("10495058912");
        usuario.setLogin("maicon");
        usuario.setSenha("123");
        usuario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("16/06/1998"));
        usuario.setGrupo(grupo);
        usuario.setAtivo(Boolean.TRUE);

        usuarioDAO.connect();
        usuarioDAO.save(usuario3);
        usuarioDAO.disconnect();
        
        usuario.setNome("Filipe Carvalho");
        usuario.setCpf("10400000000");
        usuario.setLogin("filipe");
        usuario.setSenha("123");
        usuario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1990"));
        usuario.setGrupo(grupo);
        usuario.setAtivo(Boolean.TRUE);

        usuarioDAO.connect();
        usuarioDAO.save(usuario4);
        usuarioDAO.disconnect();
    }
}

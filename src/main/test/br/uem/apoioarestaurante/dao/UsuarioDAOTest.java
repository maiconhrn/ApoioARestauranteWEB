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
        
        
        permissaoDAO.connect();
        permissao.setDescricao("ADM");
        permissao.setObservacao("Permissão para adiministrador");
        permissaoDAO.save(permissao);
        permissaoDAO.disconnect();
        
        grupoPermissaoDAO.connect();
        GrupoPermissao grupo = new GrupoPermissao();
        grupo.setDescricao("ADM");
        grupo.setObservacao("Grupo de permissões para adiministrador");
        grupo.getPermissoes().add(permissao);
        grupo.getUsuarios().add(usuario);
        grupo.getUsuarios().add(usuario2);
        grupo.getUsuarios().add(usuario3);
        grupo.getUsuarios().add(usuario4);
        grupoPermissaoDAO.save(grupo);
        grupoPermissaoDAO.disconnect();
        
        
        usuario.setNome("José Gabriel");
        usuario.setCpf("10400000000");
        usuario.setLogin("josegab");
        usuario.setSenha("123");
        usuario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1990"));
        usuario.setGrupo(grupo);
        usuario.setAtivo(Boolean.TRUE);

        usuarioDAO.connect();
        usuarioDAO.save(usuario);
        usuarioDAO.disconnect();
        
        
        usuario2.setNome("Gabriel de Oliveira Conejo");
        usuario2.setCpf("10230804942");
        usuario2.setLogin("gabriel");
        usuario2.setSenha("123");
        usuario2.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("06/04/1999"));
        usuario2.setGrupo(grupo);
        usuario2.setAtivo(Boolean.TRUE);

        usuarioDAO.connect();
        usuarioDAO.save(usuario2);
        usuarioDAO.disconnect();
        
        
        usuario3.setNome("Maicon Hesrique");
        usuario3.setCpf("10495058912");
        usuario3.setLogin("maicon");
        usuario3.setSenha("123");
        usuario3.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("16/06/1998"));
        usuario3.setGrupo(grupo);
        usuario3.setAtivo(Boolean.TRUE);

        usuarioDAO.connect();
        usuarioDAO.save(usuario3);
        usuarioDAO.disconnect();
        
        usuario4.setNome("Filipe Carvalho");
        usuario4.setCpf("10445000000");
        usuario4.setLogin("filipe");
        usuario4.setSenha("123");
        usuario4.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1990"));
        usuario4.setGrupo(grupo);
        usuario4.setAtivo(Boolean.TRUE);

        usuarioDAO.connect();
        usuarioDAO.save(usuario4);
        usuarioDAO.disconnect();
    }
}

package br.uem.apoioarestaurante.metadata.entities.builder;

import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.entities.Usuario;
import br.uem.apoioarestaurante.models.GrupoPermissao;

import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public final class UsuarioBuilder {
    private String cpf;
    private String login;
    private String nome;
    private String senha;
    private Boolean ativo;
    private Date dataNascimento;
    private List<Pedido> pedidos;
    private GrupoPermissao grupo;

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder anUsuario() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public UsuarioBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UsuarioBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder withSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioBuilder withAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public UsuarioBuilder withDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public UsuarioBuilder withPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        return this;
    }

    public UsuarioBuilder withGrupo(GrupoPermissao grupo) {
        this.grupo = grupo;
        return this;
    }

    public Usuario build() {
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setLogin(login);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setAtivo(ativo);
        usuario.setDataNascimento(dataNascimento);
        usuario.setPedidos(pedidos);
        usuario.setGrupo(grupo);
        return usuario;
    }
}

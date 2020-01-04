package br.uem.apoioarestaurante.metadata.entities.builder;

import br.uem.apoioarestaurante.metadata.entities.Usuario;
import br.uem.apoioarestaurante.models.GrupoPermissao;
import br.uem.apoioarestaurante.models.Permissao;

import java.util.List;

/**
 * @author Maicon
 */
public final class GrupoPermissaoBuilder {
    private String descricao;
    private String observacao;
    private List<Permissao> permissoes;
    private List<Usuario> usuarios;

    private GrupoPermissaoBuilder() {
    }

    public static GrupoPermissaoBuilder aGrupoPermissao() {
        return new GrupoPermissaoBuilder();
    }

    public GrupoPermissaoBuilder withDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public GrupoPermissaoBuilder withObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public GrupoPermissaoBuilder withPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
        return this;
    }

    public GrupoPermissaoBuilder withUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        return this;
    }

    public GrupoPermissao build() {
        GrupoPermissao grupoPermissao = new GrupoPermissao();
        grupoPermissao.setDescricao(descricao);
        grupoPermissao.setObservacao(observacao);
        grupoPermissao.setPermissoes(permissoes);
        grupoPermissao.setUsuarios(usuarios);
        return grupoPermissao;
    }
}

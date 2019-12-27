package br.uem.apoioarestaurante.metadata.entities.builder;

import br.uem.apoioarestaurante.models.GrupoPermissao;
import br.uem.apoioarestaurante.models.Permissao;

import java.util.List;

/**
 * @author Maicon
 */
public final class PermissaoBuilder {
    private Long id;
    private String descricao;
    private String observacao;
    private List<GrupoPermissao> grupo;

    private PermissaoBuilder() {
    }

    public static PermissaoBuilder aPermissao() {
        return new PermissaoBuilder();
    }

    public PermissaoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PermissaoBuilder withDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public PermissaoBuilder withObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public PermissaoBuilder withGrupo(List<GrupoPermissao> grupo) {
        this.grupo = grupo;
        return this;
    }

    public Permissao build() {
        return new Permissao(id, descricao, observacao, grupo);
    }
}

package br.uem.apoioarestaurante.metadata.entities;

import br.uem.apoioarestaurante.metadata.types.MovimentoEstoqueTipo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Maicon
 */
@Entity
@Table(name = "aar_movimento_estoque")
public class MovimentoEstoque implements Serializable {

    private static final long serialVersionUID = -943067544577531821L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(name = "qtd")
    private Integer qtd;

    @Column(name = "tipo")
    private MovimentoEstoqueTipo tipo;

    @Column(name = "ativo")
    private Boolean ativo;

    public MovimentoEstoque() {
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public MovimentoEstoqueTipo getTipo() {
        return tipo;
    }

    public void setTipo(MovimentoEstoqueTipo tipo) {
        this.tipo = tipo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}

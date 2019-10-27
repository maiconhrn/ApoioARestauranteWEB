package br.uem.apoioarestaurante.metadata.entities;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "aar_cliente")
public class Cliente extends Pessoa implements Serializable {

    private static final long serialVersionUID = -419545230609612864L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "ativo")
    private Boolean ativo;

    @OneToMany(mappedBy = "cliente")
    @Where(clause = "ativo = true")
    private List<Pedido> pedidos;

    public Cliente() {
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) &&
                Objects.equals(telefone, cliente.telefone) &&
                Objects.equals(endereco, cliente.endereco) &&
                Objects.equals(ativo, cliente.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, telefone, endereco, ativo);
    }
}
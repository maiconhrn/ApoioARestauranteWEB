package br.uem.apoioarestaurante.metadata.entities;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */

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

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return numero == cliente.numero &&
                Objects.equals(id, cliente.id) &&
                Objects.equals(telefone, cliente.telefone) &&
                Objects.equals(rua, cliente.rua) &&
                Objects.equals(complemento, cliente.complemento) &&
                Objects.equals(bairro, cliente.bairro) &&
                Objects.equals(cidade, cliente.cidade) &&
                Objects.equals(estado, cliente.estado) &&
                Objects.equals(ativo, cliente.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, telefone, rua, numero, complemento, bairro, cidade, estado, ativo);
    }
}
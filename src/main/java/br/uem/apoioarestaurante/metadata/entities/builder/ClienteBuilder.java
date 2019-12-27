package br.uem.apoioarestaurante.metadata.entities.builder;

import br.uem.apoioarestaurante.metadata.entities.Cliente;
import br.uem.apoioarestaurante.metadata.entities.Pedido;

import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public final class ClienteBuilder {
    private String cpf;
    private String telefone;
    private String nome;
    private String rua;
    private Integer numero;
    private Date dataNascimento;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Boolean ativo;
    private List<Pedido> pedidos;

    private ClienteBuilder() {
    }

    public static ClienteBuilder aCliente() {
        return new ClienteBuilder();
    }

    public ClienteBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClienteBuilder withTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public ClienteBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder withRua(String rua) {
        this.rua = rua;
        return this;
    }

    public ClienteBuilder withNumero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public ClienteBuilder withDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public ClienteBuilder withComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public ClienteBuilder withBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public ClienteBuilder withCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public ClienteBuilder withEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public ClienteBuilder withAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public ClienteBuilder withPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        return this;
    }

    public Cliente build() {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setNome(nome);
        cliente.setRua(rua);
        cliente.setNumero(numero);
        cliente.setDataNascimento(dataNascimento);
        cliente.setComplemento(complemento);
        cliente.setBairro(bairro);
        cliente.setCidade(cidade);
        cliente.setEstado(estado);
        cliente.setAtivo(ativo);
        cliente.setPedidos(pedidos);
        return cliente;
    }
}

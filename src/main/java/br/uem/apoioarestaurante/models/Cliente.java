package br.uem.apoioarestaurante.models;

/**
 *     Universidade Estadual de Maringá
 * 
 *  Autor: José Gabriel Júnior       Ra: 54011
 * 
 */
    
import br.uem.apoioarestaurante.metadata.entities.Person;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente extends Person {

    private static final long serialVersionUID = -419545230609612864L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
       
    @Column
    private String telefone01;
    
    @Column
    private String rua;
    
    @Column
    private int numero;
    
    @Column
    private String complemento;
    
    @Column
    private String bairro;
    
    @Column
    private String cidade;
    
    @Column
    private String estado;

    public Cliente(Long id, String telefone01, String rua, int numero, String complemento, String bairro, String cidade, String estado) {
        this.id = id;
        this.telefone01 = telefone01;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public String getTelefone01() {
        return telefone01;
    }

    public void setTelefone01(String telefone01) {
        this.telefone01 = telefone01;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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
 

}

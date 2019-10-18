package br.uem.apoioarestaurante.metadata.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Maicon
 */
@MappedSuperclass
public abstract class Person implements Serializable {

    private static final long serialVersionUID = 3072179824199336890L;

    @Column(name = "cpf", length = 15, unique = true, nullable = false)
    private String cpf;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}

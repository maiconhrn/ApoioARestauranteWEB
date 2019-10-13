package br.uem.apoioarestaurante.metadata.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Maicongay
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {

    @Column(length = 15, unique = true, nullable = false)
    private String cpf;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(cpf, person.cpf) &&
                Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, cpf, birthDate);
    }
}

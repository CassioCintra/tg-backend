package tg.espetinho.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Customer {
    @Id
    @Column(name= "cpf", length = 11)
    @Size(min = 11, max = 11)
    private String cpf;

    @Column(name = "nome")
    private String name;

    @Column(name = "telefone")
    private String telephone;

    public Customer() {
    }

    public Customer(String cpf, String name, String telephone) {
        this.cpf = cpf;
        this.name = name;
        this.telephone = telephone;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cpf, customer.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}

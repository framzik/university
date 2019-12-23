package ru.university.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "professors")
public class Professor extends AbstractEntity {
    @NotBlank
    @Size(min = 2, max = 200)
    @Column(name = "address", nullable = false)
    protected String telephone;

    @NotNull
    @Size(max = 9)
    @Column(name = "cost", nullable = false)
    private Float cost;

    public Professor() {
    }

    public Professor(Integer id, String name, String address, String telephone, Float cost) {
        super(id, name, address);
        this.telephone = telephone;
        this.cost = cost;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Professor professor = (Professor) o;

        return Objects.equals(cost, professor.cost);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}

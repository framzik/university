package ru.university.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass

public abstract class AbstractEntity extends AbstractBaseEntity {
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    protected String name;

    @NotBlank
    @Size(min=2,max = 200)
    @Column(name = "address", nullable = false)
    protected String address;


    protected AbstractEntity() {
    }

    protected AbstractEntity(Integer id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "AbstractEntity{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
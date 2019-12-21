package model;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class AbstractEntity extends AbstractBaseEntity {

    protected String name;
    protected String address;
    protected String telephone;

    protected AbstractEntity() {
    }

    protected AbstractEntity(Integer id, String name, String address, String telephone) {
        super(id);
        this.name = name;
        this.address = address;
        this.telephone = telephone;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
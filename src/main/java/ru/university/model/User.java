package ru.university.model;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public abstract class User extends AbstractNamedEntity {
    protected String email;

    private String password;

    protected boolean enabled = true;

    protected Date registered = new Date();

    @NotBlank
    @Size(min = 2, max = 200)
    @Column(name = "address", nullable = false)
    protected String address;

    public User() {
    }

    public User(Integer id, String name, String email, String password, String address) {
        this(id, name, email, password, address, true);
    }

    public User(Integer id, String name, String email, String password, String address, boolean enabled) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.address = address;
        this.enabled = enabled;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", address=" + address +
                ", enabled=" + enabled +
                ')';
    }
}

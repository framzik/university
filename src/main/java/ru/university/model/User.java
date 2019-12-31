package ru.university.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractNamedEntity {
    protected String email;

    private String password;

    protected boolean enabled = true;

    protected Date registered = new Date();

    @NotBlank
    @Size(min = 2, max = 200)
    @Column(name = "address", nullable = false)
    protected String address;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String email, String password, String address, Role role, Role... roles) {
        this(id, name, email, password, address, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, String address, boolean enabled, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.address = address;
        this.enabled = enabled;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Set<Role> getRoles() {
        return roles;
    }


    @Override
    public String toString() {
        return "User{" +
                " id=" + id +
                ", name='" + name + '\'' +
                ",email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", address='" + address + '\'' +
                ", roles=" + roles +
                '}';
    }
}
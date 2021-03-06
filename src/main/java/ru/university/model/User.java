package ru.university.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.util.CollectionUtils;
import ru.university.HasEmail;
import ru.university.View;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT distinct (u) FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email"),
        @NamedQuery(name = User.BYEMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
})
@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity implements HasEmail {
    public static final String DELETE = "User.delete";
    public static final String ALL_SORTED = "User.getAllSorted";
    public static final String BYEMAIL = "User.getByEmail";
    @Column(name = "email")
    @Email
    @NotBlank
    @Size(max = 100)
    @SafeHtml(groups = {View.Web.class})
    protected String email;

    @Column(name = "password")
    @NotBlank
    @Size(min = 5, max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled")
    protected boolean enabled = true;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected Date registered = new Date();

    @NotBlank
    @Size(min = 2, max = 200)
    @Column(name = "address", nullable = false)
    @SafeHtml
    protected String address;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("name")
    //@JsonIgnore
    protected List<Course> courses;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getAddress(), u.isEnabled(), u.getRegistered(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, String address, Role role, Role... roles) {
        this(id, name, email, password, address, true, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, String address, boolean enabled, Date registered, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.address = address;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    @Override
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
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

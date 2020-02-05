package ru.university.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = Course.DELETE, query = "DELETE FROM Course c WHERE c.id = :id AND c.user.id = :userId"),
        @NamedQuery(name = Course.ALL_SORTED, query = "SELECT c FROM Course c  WHERE c.user.id = :userId ORDER BY c.name"),
        @NamedQuery(name = Course.BYNAME, query = "SELECT c FROM Course c  WHERE c.name = ?1 AND c.user.id = ?2"),
        @NamedQuery(name = Course.BETWEEN_COST, query = "SELECT c FROM Course c  WHERE c.user.id = :userId AND c.cost >= :startCost AND c.cost <= :endCost ORDER BY c.name")
})
@Entity
@Table(name = "university_courses")
public class Course extends AbstractNamedEntity {

    public static final String DELETE = "Course.delete";
    public static final String ALL_SORTED = "Course.getAllSorted";
    public static final String BYNAME = "Course.getByName";
    public static final String BETWEEN_COST = "Course.getBetweenCost";

    @NotNull
    @Column(name = "number", unique = true, nullable = false)
    private int number;

    @NotNull
    @Column(name = "cost", unique = true, nullable = false)
    private float cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Course() {
    }

    public Course(Integer id, String name, int number, float cost) {
        super(id, name);
        this.number = number;
        this.cost = cost;
    }

    public Course(String name, int number, float cost) {
        this.id = null;
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UniversityCourse{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", cost=" + cost +
                ", id=" + id +
                '}';
    }
}

package ru.university.to;

import java.beans.ConstructorProperties;

public class CourseTo extends BaseTo {
    private final String name;
    private final int number;
    private final float cost;

    @ConstructorProperties({"id", "name", "number", "cost", "cheep"})
    public CourseTo(Integer id, String name, int number, float cost) {
        super(id);
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseTo courseTo = (CourseTo) o;

        if (number != courseTo.number) return false;
        if (Float.compare(courseTo.cost, cost) != 0) return false;
        return name != null ? name.equals(courseTo.name) : courseTo.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + number;
        result = 31 * result + (cost != +0.0f ? Float.floatToIntBits(cost) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CourseTo{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", cost=" + cost +
                ", id=" + id +
                '}';
    }
}

package ru.university.to;

public class CourseTo extends BaseTo {
    String name;
    int number;
    float cost;

    public CourseTo() {
    }

    public CourseTo(Integer id, String name, int number, float cost) {
        super(id);
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

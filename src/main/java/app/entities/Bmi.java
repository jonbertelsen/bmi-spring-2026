package app.entities;

import java.time.LocalDateTime;

public class Bmi {

    private int id;
    private int height;
    private int weight;
    private double bmi;
    private String name;
    private String verdict;
    private LocalDateTime created;

    public Bmi(int height, int weight, double bmi, String name, String verdict, LocalDateTime created) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.name = name;
        this.verdict = verdict;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public double getBmi() {
        return bmi;
    }

    public String getName() {
        return name;
    }

    public String getVerdict() {
        return verdict;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Bmi{" +
                "id=" + id +
                ", height=" + height +
                ", weight=" + weight +
                ", bmi=" + bmi +
                ", name='" + name + '\'' +
                ", verdict='" + verdict + '\'' +
                ", created=" + created +
                '}';
    }
}

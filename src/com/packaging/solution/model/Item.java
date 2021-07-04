package com.packaging.solution.model;

public class Item {
    private int number;
    private double weight;
    private double cost;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "[" + number + ", " + weight + ", " + cost + "]";
    }
}

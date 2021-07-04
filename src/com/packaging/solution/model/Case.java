package com.packaging.solution.model;

import java.util.List;

public class Case {
    private double totalWeight;
    private List<Item> items;

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder text =  new StringBuilder(totalWeight + " : ");

        for(Item item : items) {
            text.append(item.toString()).append(" ");
        }

        return text.toString();
    }
}

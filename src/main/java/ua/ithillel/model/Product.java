package ua.ithillel.model;

import lombok.Data;

@Data
public class Product {
    private static int lastId = 0;
    private Integer id;
    private String name;
    private double cost;

    public Product(String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.id = getNextId();
    }

    private static synchronized int getNextId() {
        return ++lastId;
    }
}

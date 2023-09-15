package ua.ithillel.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Order {
    private static int lastId = 0;
    private Integer id;
    private LocalDate date;
    private double cost;
    private List<Product> products;

    public Order(List<Product> products) {
        this.id = getNextId();
        this.date = LocalDate.now();
        this.products = products;
        this.cost = calculateTotalCost(products);
    }

    private static synchronized int getNextId() {
        return ++lastId;
    }

    private double calculateTotalCost(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getCost)
                .sum();
    }
}

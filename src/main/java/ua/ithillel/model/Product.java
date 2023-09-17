package ua.ithillel.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Product {
    private static int lastId = 0;
    private Integer id;
    private String name;
    private Double cost;

    public Product(String name, Double cost) {
        this.name = name;
        this.cost = cost;
        this.id = getNextId();
    }

    private static synchronized int getNextId() {
        return ++lastId;
    }
}

package ua.ithillel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
    private static int lastId = 0;
    private Integer id;
    @JsonFormat(pattern = "dd.MM.yyyy")
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

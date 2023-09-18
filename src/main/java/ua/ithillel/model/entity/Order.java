package ua.ithillel.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "cost")
    private double cost;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Order(List<Product> products) {
        this.date = LocalDate.now();
        this.products = products;
        this.cost = calculateTotalCost(products);
    }

    private double calculateTotalCost(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getCost)
                .sum();
    }

    public void recalculateCost(){
        this.cost = calculateTotalCost(this.products);
    }
}

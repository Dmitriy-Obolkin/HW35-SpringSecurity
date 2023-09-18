package ua.ithillel.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Double cost;

    public Product(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }
}

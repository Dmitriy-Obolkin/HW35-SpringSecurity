package ua.ithillel.spring.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_order")
public class Order implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double cost;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Override
    public String toString() {
        String productIds = orderProducts.stream()
                .map(op -> op.getProduct().getId())
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", cost=" + cost +
                ", productIds=[" + productIds + "]" +
                '}';
    }

}

package ua.ithillel.spring.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double cost;

    @Builder.Default
    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public void ds(){
        for (int i = 0; i < 10; i++) {
            Product product = orderProducts.get(i).getProduct();
        }
    }
}

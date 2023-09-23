package ua.ithillel.spring.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ProductDTO {
    private Integer id;
    private String name;
    private Double cost;
}

package ua.ithillel.spring.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private Double cost;
}

package ua.ithillel.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private Double cost;
}

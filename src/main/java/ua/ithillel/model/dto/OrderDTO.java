package ua.ithillel.model.dto;

import lombok.Data;
import ua.ithillel.model.entity.Product;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    private Integer id;
    private LocalDate date;
    private Double cost;
    private List<ProductDTO> products;
}

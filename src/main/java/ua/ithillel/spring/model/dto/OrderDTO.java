package ua.ithillel.spring.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    private Integer id;
    private LocalDate date;
    private Double cost;
    private List<ProductDTO> productDTOs;
}

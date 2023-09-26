package ua.ithillel.spring.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode
@Builder
public class OrderDTO {
    private Integer id;
    private LocalDate date;
    private Double cost;
    private List<ProductDTO> productDTOs;
}

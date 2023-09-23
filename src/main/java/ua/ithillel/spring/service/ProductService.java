package ua.ithillel.spring.service;

import ua.ithillel.spring.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Integer id);

    List<ProductDTO> findAll();
}

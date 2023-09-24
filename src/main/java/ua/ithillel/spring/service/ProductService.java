package ua.ithillel.spring.service;

import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Integer id) throws IllegalArgumentException, EntityNotFoundException;

    List<ProductDTO> findAll() throws EntityNotFoundException;

    ProductDTO addProduct(ProductDTO productDTO) throws RuntimeException;

    ProductDTO removeProductById(Integer id) throws EntityNotFoundException, IllegalArgumentException;
}

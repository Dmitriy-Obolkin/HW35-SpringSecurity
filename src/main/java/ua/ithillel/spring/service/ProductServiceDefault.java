package ua.ithillel.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.database.entity.Product;
import ua.ithillel.spring.database.repository.ProductRepository;
import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.ProductDTO;
import ua.ithillel.spring.model.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceDefault implements ProductService{

    public final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDTO findById(Integer id)
            throws IllegalArgumentException, EntityNotFoundException {

        validateId(id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found."));

        return productMapper.productToProductDTO(product);
    }

    @Override
    @Transactional
    public List<ProductDTO> findAll()
            throws EntityNotFoundException {

        return productRepository.findAll()
                .map(products -> products.stream()
                        .map(productMapper::productToProductDTO)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new EntityNotFoundException("List Products is empty."));
    }

    @Override
    @Transactional
    public ProductDTO addProduct(ProductDTO productDTO)
            throws RuntimeException {

        Product product = productMapper.productDTOToProduct(productDTO);
        Product savedProductOpt = productRepository.save(product)
                .orElseThrow(() -> new RuntimeException("Failed to save the product: " + productDTO));

        return productMapper.productToProductDTO(savedProductOpt);
    }

    @Override
    @Transactional
    public ProductDTO removeProductById(Integer id)
            throws EntityNotFoundException, IllegalArgumentException {

        validateId(id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found."));
        productRepository.deleteById(id);

        return productMapper.productToProductDTO(product);
    }

    private void validateId(Integer id)
            throws IllegalArgumentException {

        if(id < 0){
            throw new IllegalArgumentException("Invalid ID: ID should not be negative. Given ID: " + id);
        }
    }
}

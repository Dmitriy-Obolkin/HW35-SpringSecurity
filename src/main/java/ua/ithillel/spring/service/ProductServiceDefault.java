package ua.ithillel.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.database.repository.ProductRepository;
import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.ProductDTO;
import ua.ithillel.spring.model.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceDefault implements ProductService{

    public final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ProductDTO findById(Integer id){

        validateId(id);

        Optional<ProductDTO> productDTOOpt = productRepository.findById(id)
                .map(productMapper::productToProductDTO);

        if(productDTOOpt.isEmpty()){
            throw new EntityNotFoundException("Product with ID " + id + " not found.");
        }

        return productDTOOpt.get();
    }

    @Override
    public List<ProductDTO> findAll() {

        Optional<List<ProductDTO>> productDTOsOpt = productRepository.findAll()
                .map(products -> products.stream()
                        .map(productMapper::productToProductDTO)
                        .collect(Collectors.toList()));

        if(productDTOsOpt.isEmpty()){
            throw new EntityNotFoundException("List Products is empty");
        }

        return productDTOsOpt.get();
    }

    private void validateId(Integer id){
        if(id < 0){
            throw new IllegalArgumentException("Invalid ID: ID should not be negative. Given ID: " + id);
        }
    }
}

package ua.ithillel.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.database.repository.ProductRepository;
import ua.ithillel.spring.model.dto.ProductDTO;
import ua.ithillel.spring.model.mapper.ProductMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public Optional<ProductDTO> findById(Integer id){
        return productRepository.findById(id)
                .map(productMapper::productToProductDTO);
    }

}

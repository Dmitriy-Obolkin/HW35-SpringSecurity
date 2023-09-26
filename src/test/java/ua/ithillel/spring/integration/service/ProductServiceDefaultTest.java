package ua.ithillel.spring.integration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.ProductDTO;
import ua.ithillel.spring.service.ProductServiceDefault;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductServiceDefaultTest {

    @Autowired
    private ProductServiceDefault productService;

    ProductDTO testProduct1;
    ProductDTO testProduct2;

    @BeforeEach
    void setUp() {

        testProduct1 = ProductDTO.builder()
                .name("TestProduct1")
                .cost(100d)
                .build();
        testProduct2 = ProductDTO.builder()
                .name("TestProduct2")
                .cost(200d)
                .build();
    }

    @Test
    void saveTest() {

        ProductDTO savedProduct = productService.addProduct(testProduct1);

        assertNotNull(savedProduct);
        assertEquals(testProduct1.getName(), savedProduct.getName());
        assertEquals(testProduct1.getCost(), savedProduct.getCost());
    }

    @Test
    void findByIdTest() {

        ProductDTO savedProduct = productService.addProduct(testProduct1);
        assertNotNull(savedProduct.getId());
        ProductDTO productById = productService.findById(savedProduct.getId());

        assertNotNull(productById);
        assertEquals(testProduct1.getName(), productById.getName());
        assertEquals(testProduct1.getCost(), productById.getCost());
    }

    @Test
    void findAllTest() {

        productService.addProduct(testProduct1);
        productService.addProduct(testProduct2);

        List<ProductDTO> products = productService.findAll();

        assertNotNull(products);
        assertTrue(products.size() >= 2);
        assertTrue(products.stream()
                .anyMatch(p -> testProduct1.getName().equals(p.getName())));
        assertTrue(products.stream()
                .anyMatch(p -> testProduct2.getName().equals(p.getName())));
    }

    @Test
    void deleteByIdTest() {

        ProductDTO savedProduct = productService.addProduct(testProduct1);
        assertNotNull(productService.findById(savedProduct.getId()));

        productService.removeProductById(savedProduct.getId());

        assertThrows(EntityNotFoundException.class, () -> productService.findById(savedProduct.getId()));
    }
}

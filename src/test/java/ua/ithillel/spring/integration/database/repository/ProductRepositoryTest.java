package ua.ithillel.spring.integration.database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.database.entity.Product;
import ua.ithillel.spring.database.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    Product testProduct1;
    Product testProduct2;

    @BeforeEach
    void setUp() {

        testProduct1 = Product.builder()
                .name("TestProduct1")
                .cost(10d)
                .build();
        testProduct2 = Product.builder()
                .name("TestProduct2")
                .cost(20d)
                .build();
    }

    @Test
    void saveAndFindByIdTest() {

        Optional<Product> savedProduct = productRepository.save(testProduct1);
        assertTrue(savedProduct.isPresent());

        Optional<Product> productById = productRepository.findById(savedProduct.get().getId());

        assertTrue(productById.isPresent());
        assertEquals(testProduct1.getName(), productById.get().getName());
        assertEquals(testProduct1.getCost(), productById.get().getCost());
        assertEquals(testProduct1.getOrderProducts(), productById.get().getOrderProducts());
    }

    @Test
    void findAllTest() {

        productRepository.save(testProduct1);
        productRepository.save(testProduct2);

        Optional<List<Product>> products = productRepository.findAll();

        assertTrue(products.isPresent());
        assertTrue(products.get().size() >= 2);
        assertTrue(products.get().stream()
                .anyMatch(p -> testProduct1.getName().equals(p.getName())));
        assertTrue(products.get().stream()
                .anyMatch(p -> testProduct2.getName().equals(p.getName())));
    }

    @Test
    void deleteByIdTest() {

        Optional<Product> savedProduct = productRepository.save(testProduct1);

        assertTrue(savedProduct.isPresent());
        assertTrue(productRepository.findById(savedProduct.get().getId()).isPresent());

        productRepository.deleteById(savedProduct.get().getId());

        assertFalse(productRepository.findById(savedProduct.get().getId()).isPresent());
    }
}
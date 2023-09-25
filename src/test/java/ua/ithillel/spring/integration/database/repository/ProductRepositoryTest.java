package ua.ithillel.spring.integration.database.repository;

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

    @Test
    void saveAndFindByIdTest() {

        Product product = Product.builder()
                .name("TestProduct1")
                .cost(10d)
                .build();

        Optional<Product> savedProduct = productRepository.save(product);
        assertTrue(savedProduct.isPresent());

        Optional<Product> productById = productRepository.findById(savedProduct.get().getId());

        assertTrue(productById.isPresent());
        assertEquals(product.getName(), productById.get().getName());
        assertEquals(product.getCost(), productById.get().getCost());
        assertEquals(product.getOrderProducts(), productById.get().getOrderProducts());
    }

    @Test
    void findAllTest() {

        Product product1 = Product.builder()
                .name("TestProduct1")
                .cost(10d)
                .build();
        Product product2 = Product.builder()
                .name("TestProduct2")
                .cost(20d)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);

        Optional<List<Product>> products = productRepository.findAll();

        assertTrue(products.isPresent());
        assertTrue(products.get().size() >= 2);
        assertTrue(products.get().stream().anyMatch(p -> product1.getName().equals(p.getName())));
        assertTrue(products.get().stream().anyMatch(p -> product2.getName().equals(p.getName())));
    }

    @Test
    void deleteById() {

        Product product1 = Product.builder()
                .name("TestProduct1")
                .cost(10d)
                .build();

        Optional<Product> savedProduct = productRepository.save(product1);

        assertTrue(savedProduct.isPresent());
        assertTrue(productRepository.findById(savedProduct.get().getId()).isPresent());

        productRepository.deleteById(savedProduct.get().getId());

        assertFalse(productRepository.findById(savedProduct.get().getId()).isPresent());
    }
}
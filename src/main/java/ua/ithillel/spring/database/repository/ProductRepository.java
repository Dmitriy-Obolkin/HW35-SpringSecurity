package ua.ithillel.spring.database.repository;

import org.springframework.data.repository.Repository;
import ua.ithillel.spring.database.entity.Product;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ProductRepository extends Repository<Product, Integer> {

    Optional<Product> findById(Integer id);

    Optional<List<Product>> findAll();

    Optional<Product> save(Product product);

    void deleteById(Integer id);
}

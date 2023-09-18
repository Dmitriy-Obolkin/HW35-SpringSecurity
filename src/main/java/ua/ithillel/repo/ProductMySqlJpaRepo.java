package ua.ithillel.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.ithillel.model.entity.Product;
import ua.ithillel.util.HibernateTransactionManager;

@Repository
@RequiredArgsConstructor
public class ProductMySqlJpaRepo implements ProductRepo{
    private final HibernateTransactionManager transactionManager;

    @Override
    public Product findById(Integer id) {
        validateId(id);
        return transactionManager.executeInsideTransaction(session -> session.find(Product.class, id));
    }

    @Override
    public Product save(Product product) {
        return transactionManager.executeInsideTransaction(session -> {
            session.persist(product);
            session.flush();
            return product;
        });
    }

    private void validateId(Integer id) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid ID: ID should not be negative. Given ID: " + id);
        }
    }
}

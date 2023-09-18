package ua.ithillel.repo;

import ua.ithillel.model.entity.Product;

public interface ProductRepo {
    public Product findById(Integer id);
    public Product save(Product product);
}

package ua.ithillel.spring.database.repository;

import org.springframework.data.repository.Repository;
import ua.ithillel.spring.database.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends Repository<Order, Integer> {

    Optional<Order> findById(Integer id);

    Optional<List<Order>> findAll();

    Optional<Order> save(Order order);

    void deleteById(Integer id);
}

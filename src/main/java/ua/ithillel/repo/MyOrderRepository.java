package ua.ithillel.repo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ua.ithillel.exception.OrderAlreadyExistsException;
import ua.ithillel.exception.OrderNotFoundException;
import ua.ithillel.model.Order;
import ua.ithillel.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MyOrderRepository implements OrderRepo {
    private final Map<Integer, Order> orders = new HashMap<>();

    @PostConstruct
    public void init(){
        Product product1 = new Product("Product1", 50d);
        Product product2 = new Product("Product2", 250d);
        Product product3 = new Product("Product3", 80.5d);

        Order order1 = new Order(List.of(product2));
        Order order2 = new Order(List.of(product1, product2, product3));

        add(order1);
        add(order2);
    }

    @Override
    public List<Order> getAll(){
        return orders.values().stream().toList();
    }
    @Override
    public Order getById(int id){
        validateId(id);
        return Optional.ofNullable(orders.get(id))
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public boolean add(Order order){
        if(existsById(order.getId())){
            throw new OrderAlreadyExistsException(order.getId());
        }

        orders.put(order.getId(), order);
        return true;
    }

    @Override
    public boolean remove(int id){
        validateId(id);
        if(orders.remove(id) == null){
            throw new OrderNotFoundException(id);
        }
        return true;
    }

    @Override
    public boolean existsById(int id) {
        return orders.containsKey(id);
    }

    private void validateId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid ID: ID should not be negative. Given ID: " + id);
        }
    }
}

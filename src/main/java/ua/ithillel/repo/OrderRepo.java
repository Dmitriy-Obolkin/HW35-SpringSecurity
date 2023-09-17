package ua.ithillel.repo;

import ua.ithillel.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepo {
    List<Order> getAll();
    Order getById(int id);
    boolean add(Order order);
    boolean remove(int id);
    boolean existsById(int id);
}

package ua.ithillel.repo;

import ua.ithillel.model.entity.Order;

import java.util.List;

public interface OrderRepo {
    List<Order> getAll();
    Order getById(Integer id);
    Order add(Order order);
    boolean remove(Integer id);
}

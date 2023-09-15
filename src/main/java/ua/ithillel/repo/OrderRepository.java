package ua.ithillel.repo;

import ua.ithillel.exception.OrderNotFoundException;
import ua.ithillel.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class OrderRepository {
    private Map<Integer, Order> orders = new HashMap<>();

    public Map<Integer, Order> getOrders(){
        return orders;
    }
    public Order getOrderById(int id){
        validateId(id);
        return Optional.ofNullable(orders.get(id))
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public void addOrder(Order order){
        orders.put(order.getId(), order);
    }

    public void removeOrder(int id){
        validateId(id);
        if(orders.remove(id) == null){
            throw new OrderNotFoundException(id);
        }
    }

    private void validateId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid ID: ID should not be negative. Given ID: " + id);
        }
    }
}

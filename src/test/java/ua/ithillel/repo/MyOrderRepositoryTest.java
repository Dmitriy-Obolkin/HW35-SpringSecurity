package ua.ithillel.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ithillel.exception.OrderAlreadyExistsException;
import ua.ithillel.exception.OrderNotFoundException;
import ua.ithillel.model.Order;
import ua.ithillel.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyOrderRepositoryTest {
    private MyOrderRepository repository;

    @BeforeEach
    public void setUp(){
        repository = new MyOrderRepository();
        repository.init();
    }

    @Test
    public void getAllTest() {
        List<Order> allOrders = repository.getAll();
        assertNotNull(allOrders);
        assertEquals(2, allOrders.size());
    }

    @Test
    public void getByIdTestValid() {
        Order order1 = new Order(List.of(new Product("product", 10d)));
        repository.add(order1);
        int order1Id = order1.getId();

        Order order = repository.getById(order1Id);
        assertNotNull(order);
        assertEquals(order1Id, order.getId());
    }

    @Test
    public void getByIdTestInvalidId() {
        assertThrows(OrderNotFoundException.class, () -> repository.getById(1000));
    }

    @Test
    public void getByIdTestNegativeId() {
        assertThrows(IllegalArgumentException.class, () -> repository.getById(-1));
    }

    @Test
    public void addTest() {
        Product product4 = new Product("Product4", 868d);
        Order newOrder = new Order(List.of(product4));

        assertTrue(repository.add(newOrder));
        assertNotNull(repository.getById(newOrder.getId()));
    }

    @Test
    public void addTestExisting(){
        Product product4 = new Product("Product4", 868d);
        Order newOrder = new Order(List.of(product4));
        repository.add(newOrder);

        assertThrows(OrderAlreadyExistsException.class, () -> repository.add(newOrder));
    }

    @Test
    public void removeTest() {
        Product product4 = new Product("Product4", 868d);
        Order newOrder = new Order(List.of(product4));
        repository.add(newOrder);

        assertTrue(repository.remove(newOrder.getId()));
        assertThrows(OrderNotFoundException.class, () -> repository.getById(newOrder.getId()));
    }

    @Test
    public void removeTestNonExisting(){
        assertThrows(OrderNotFoundException.class, () -> repository.remove(1000));
    }

    @Test
    public void removeTestNegativeId(){
        assertThrows(IllegalArgumentException.class, () -> repository.remove(-1));
    }

    @Test
    void existsById() {
        Product product4 = new Product("Product4", 868d);
        Order newOrder = new Order(List.of(product4));
        repository.add(newOrder);

        assertTrue(repository.existsById(newOrder.getId()));
        assertFalse(repository.existsById(1000));
    }
}
package ua.ithillel.spring.integration.database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.database.entity.Order;
import ua.ithillel.spring.database.entity.OrderProduct;
import ua.ithillel.spring.database.entity.Product;
import ua.ithillel.spring.database.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    Order testOrder1;
    Order testOrder2;

    @BeforeEach
    void setUp() {

        Product testProduct1 = Product.builder()
                .id(1)
                .name("TestProduct1")
                .cost(10d)
                .build();
        Product testProduct2 = Product.builder()
                .id(2)
                .name("TestProduct2")
                .cost(20d)
                .build();

        testOrder1 = Order.builder()
                .date(LocalDate.now())
                .cost(30d)
                .build();
        testOrder2 = Order.builder()
                .date(LocalDate.now())
                .cost(10d)
                .build();

        OrderProduct orderProduct1 = OrderProduct.builder()
                .order(testOrder1)
                .product(testProduct1)
                .build();
        OrderProduct orderProduct2 = OrderProduct.builder()
                .order(testOrder1)
                .product(testProduct2)
                .build();
        OrderProduct orderProduct3 = OrderProduct.builder()
                .order(testOrder2)
                .product(testProduct1)
                .build();

        testOrder1.setOrderProducts(List.of(orderProduct1, orderProduct2));
        testOrder2.setOrderProducts(List.of(orderProduct3));
    }

    @Test
    void saveAndFindByIdTest() {

        Optional<Order> savedOrder = orderRepository.save(testOrder1);
        assertTrue(savedOrder.isPresent());

        Optional<Order> orderById = orderRepository.findById(savedOrder.get().getId());

        assertTrue(orderById.isPresent());
        assertEquals(testOrder1, orderById.get());
        assertEquals(2, orderById.get().getOrderProducts().size());
    }

    @Test
    void findAllTest() {

        orderRepository.save(testOrder1);
        orderRepository.save(testOrder2);

        Optional<List<Order>> orders = orderRepository.findAll();

        assertTrue(orders.isPresent());
        assertTrue(orders.get().size() >= 2);
        assertTrue(orders.get().stream()
                .anyMatch(o -> testOrder1.getId().equals(o.getId())));
        assertTrue(orders.get().stream()
                .anyMatch(o -> testOrder2.getId().equals(o.getId())));
    }

    @Test
    void deleteByIdTest() {

        Optional<Order> savedOrder = orderRepository.save(testOrder1);

        assertTrue(savedOrder.isPresent());
        assertTrue(orderRepository.findById(savedOrder.get().getId()).isPresent());

        orderRepository.deleteById(savedOrder.get().getId());

        assertFalse(orderRepository.findById(savedOrder.get().getId()).isPresent());
    }
}
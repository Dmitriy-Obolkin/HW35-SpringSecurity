package ua.ithillel.spring.integration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.OrderDTO;
import ua.ithillel.spring.model.dto.ProductDTO;
import ua.ithillel.spring.service.OrderServiceDefault;
import ua.ithillel.spring.service.ProductServiceDefault;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceDefaultTest {

    @Autowired
    private OrderServiceDefault orderService;
    @Autowired
    private ProductServiceDefault productService;

    OrderDTO testOrder1;
    OrderDTO testOrder2;

    @BeforeEach
    void setUp() {

        assertDoesNotThrow(() -> productService.findById(1));
        assertDoesNotThrow(() -> productService.findById(2));
        ProductDTO testProduct1 = productService.findById(1);
        ProductDTO testProduct2 = productService.findById(2);

        testOrder1 = OrderDTO.builder()
                .date(LocalDate.now())
                .cost(testProduct1.getCost()+testProduct2.getCost())
                .productDTOs(List.of(testProduct1, testProduct2))
                .build();

        testOrder2 = OrderDTO.builder()
                .date(LocalDate.now())
                .cost(testProduct2.getCost())
                .productDTOs(List.of(testProduct2))
                .build();
    }

    @Test
    void addOrderTest() {

        OrderDTO savedOrder = orderService.addOrder(testOrder1);
        assertNotNull(savedOrder.getId());
        testOrder1.setId(savedOrder.getId());

        assertNotNull(savedOrder);
        assertEquals(testOrder1, savedOrder);
        assertEquals(2, savedOrder.getProductDTOs().size());
    }

    @Test
    void findById() {

        OrderDTO savedOrder = orderService.addOrder(testOrder1);
        assertNotNull(savedOrder.getId());
        testOrder1.setId(savedOrder.getId());
        OrderDTO orderById = orderService.findById(savedOrder.getId());

        assertNotNull(orderById);
        assertEquals(testOrder1, orderById);
        assertEquals(2, orderById.getProductDTOs().size());
    }

    @Test
    void findAll() {

        OrderDTO savedOrderDTO1 = orderService.addOrder(testOrder1);
        assertNotNull(savedOrderDTO1.getId());
        testOrder1.setId(savedOrderDTO1.getId());

        OrderDTO savedOrderDTO2 = orderService.addOrder(testOrder2);
        assertNotNull(savedOrderDTO2.getId());
        testOrder2.setId(savedOrderDTO2.getId());

        List<OrderDTO> orders = orderService.findAll();

        assertNotNull(orders);
        assertTrue(orders.size() >= 2);
        assertTrue(orders.stream()
                .anyMatch(o -> testOrder1.getId().equals(o.getId())));
        assertTrue(orders.stream()
                .anyMatch(o -> testOrder2.getId().equals(o.getId())));
    }

    @Test
    void removeOrderByIdTest() {

        OrderDTO savedOrder = orderService.addOrder(testOrder1);
        assertNotNull(savedOrder.getId());
        assertNotNull(orderService.findById(savedOrder.getId()));

        orderService.removeOrderById(savedOrder.getId());

        assertThrows(EntityNotFoundException.class,
                () -> orderService.findById(savedOrder.getId()));
    }
}
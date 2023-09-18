package ua.ithillel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.exception.OrderNotFoundException;
import ua.ithillel.model.entity.Order;
import ua.ithillel.model.entity.Product;
import ua.ithillel.repo.OrderMySqlJpaRepo;
import ua.ithillel.repo.ProductMySqlJpaRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderMySqlJpaRepo orderRepository;
    private final ProductMySqlJpaRepo productRepository;

    @GetMapping
    ResponseEntity<List<Order>> getAll(){
        return ResponseEntity.ok(orderRepository.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Order> getById(@PathVariable("id") Integer id) throws OrderNotFoundException, IllegalArgumentException {
        Order order = orderRepository.getById(id);
        if(order == null){
            throw new OrderNotFoundException(id);
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        List<Product> checkedProducts = new ArrayList<>();

        for(Product product : order.getProducts()) {
            if(product.getId() == null){
                Product savedProduct = productRepository.save(product);
                checkedProducts.add(savedProduct);
            } else {
                Product existingProduct = productRepository.findById(product.getId());
                if (existingProduct != null){
                    checkedProducts.add(existingProduct);
                }
            }
        }

        order.setProducts(checkedProducts);
        order.recalculateCost();
        Order savedOrder = orderRepository.add(order);
        return ResponseEntity.ok(savedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> removeOrder(@PathVariable("id") Integer id) throws OrderNotFoundException, IllegalArgumentException {
        Order order = orderRepository.getById(id);
        orderRepository.remove(id);
        return ResponseEntity.ok(order);
    }
}

package ua.ithillel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.exception.OrderNotFoundException;
import ua.ithillel.model.Order;
import ua.ithillel.repo.MyOrderRepository;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final MyOrderRepository repository;

    @GetMapping
    ResponseEntity<List<Order>> getAll(){
        return ResponseEntity.ok(repository.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Order> getById(@PathVariable("id") Integer id) throws OrderNotFoundException, IllegalArgumentException {
        return ResponseEntity.ok(repository.getById(id));
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        repository.add(order);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> removeOrder(@PathVariable("id") Integer id) throws OrderNotFoundException, IllegalArgumentException {
        Order order = repository.getById(id);
        repository.remove(id);
        return ResponseEntity.ok(order);
    }
}

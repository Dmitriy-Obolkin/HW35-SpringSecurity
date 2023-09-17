package ua.ithillel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    ResponseEntity<Order> getById(@PathVariable("id") int id) throws OrderNotFoundException {
        return ResponseEntity.ok(repository.getById(id));
    }
}

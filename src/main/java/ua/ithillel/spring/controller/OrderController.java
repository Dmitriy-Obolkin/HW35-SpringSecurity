package ua.ithillel.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.OrderDTO;
import ua.ithillel.spring.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    ResponseEntity<OrderDTO> findById(@PathVariable("id") Integer id)
            throws EntityNotFoundException, IllegalArgumentException {

        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping
    ResponseEntity<List<OrderDTO>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

//    @PostMapping
//    public ResponseEntity<Order> addOrder(@RequestBody Order order){
//        Order savedorder = orderService.addOrder(order);
//        return ResponseEntity.ok(savedorder);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Order> removeOrder(@PathVariable("id") Integer id) throws OrderNotFoundException, IllegalArgumentException {
//        Order removedOrder = orderService.removeOrder(id);
//        return ResponseEntity.ok(removedOrder);
//    }
}

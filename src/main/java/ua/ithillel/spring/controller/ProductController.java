package ua.ithillel.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.spring.model.dto.ProductDTO;
import ua.ithillel.spring.service.ProductService;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> findById(@PathVariable("id") Integer id) throws IllegalArgumentException {

        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    ResponseEntity<List<ProductDTO>> findAll(){

        return ResponseEntity.ok(productService.findAll());
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

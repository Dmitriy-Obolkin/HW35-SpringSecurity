package ua.ithillel.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.spring.model.dto.ProductDTO;
import ua.ithillel.spring.service.ProductService;

import java.util.Optional;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    public final ProductService productService;

//    @GetMapping
//    ResponseEntity<List<Order>> getAll(){
//        return ResponseEntity.ok(orderRepository.getAll());
//    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<ProductDTO>> findById(@PathVariable("id") Integer id) throws IllegalArgumentException {
        Optional<ProductDTO> productDTO = productService.findById(id);
//        if(productDTO == null){
//            throw new OrderNotFoundException(id);
//        }
        return ResponseEntity.ok(productDTO);
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

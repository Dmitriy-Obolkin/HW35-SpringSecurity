package ua.ithillel.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.ProductDTO;
import ua.ithillel.spring.service.ProductService;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        try{
            return ResponseEntity.ok(productService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    ResponseEntity<?> findAll() {

        try{
            return ResponseEntity.ok(productService.findAll());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {

        return ResponseEntity.ok(productService.addProduct(productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable("id") Integer id) {

        try{
            return ResponseEntity.ok(productService.removeProductById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}

//package ua.ithillel.service;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import ua.ithillel.exception.OrderNotFoundException;
//import ua.ithillel.model.entity.Order;
//import ua.ithillel.model.entity.Product;
//import ua.ithillel.repo.OrderMySqlJpaRepo;
//import ua.ithillel.repo.ProductMySqlJpaRepo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class OrderService {
//    private final ProductMySqlJpaRepo productRepository;
//    private final OrderMySqlJpaRepo orderRepository;
//
//    public Order addOrder(Order order) {
//        List<Product> checkedProducts = new ArrayList<>();
//
//        for(Product product : order.getProducts()) {
//            if(product.getId() == null){
//                Product savedProduct = productRepository.save(product);
//                checkedProducts.add(savedProduct);
//            } else {
//                Product existingProduct = productRepository.findById(product.getId());
//                if (existingProduct != null){
//                    checkedProducts.add(existingProduct);
//                }
//            }
//        }
//
//        order.setProducts(checkedProducts);
//        order.recalculateCost();
//        return orderRepository.add(order);
//    }
//
//    public Order removeOrder(Integer orderId){
//        Order orderById = orderRepository.getById(orderId);
//
//        if(orderById == null){
//            throw new OrderNotFoundException(orderId);
//        }
//
//        orderRepository.remove(orderById);
//        return orderById;
//    }
//}

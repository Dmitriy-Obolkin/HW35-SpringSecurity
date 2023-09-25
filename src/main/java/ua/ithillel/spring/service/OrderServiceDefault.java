package ua.ithillel.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.database.entity.Order;
import ua.ithillel.spring.database.entity.OrderProduct;
import ua.ithillel.spring.database.entity.Product;
import ua.ithillel.spring.database.repository.OrderRepository;
import ua.ithillel.spring.database.repository.ProductRepository;
import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.OrderDTO;
import ua.ithillel.spring.model.mapper.OrderMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceDefault implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderDTO findById(Integer id)
            throws IllegalArgumentException, EntityNotFoundException {

        validateId(id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with ID " + id + " not found."));

        return orderMapper.orderToOrderDTO(order);
    }

    @Override
    @Transactional
    public List<OrderDTO> findAll()
            throws EntityNotFoundException {

        return orderRepository.findAll()
                .map(orders -> orders.stream()
                        .map(orderMapper::orderToOrderDTO)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new EntityNotFoundException("List Orders is empty"));
    }

    @Override
    @Transactional
    public OrderDTO addOrder(OrderDTO orderDTO)
            throws EntityNotFoundException, RuntimeException {


        Order order = orderMapper.orderDTOToOrder(orderDTO);
        Order savedOrder = orderRepository.save(order)
                .orElseThrow(() -> new RuntimeException("Failed to save the order: " + order));

        List<OrderProduct> orderProducts = savedOrder.getOrderProducts();
        for (OrderProduct op : orderProducts) {
            Product product = productRepository.findById(op.getProduct().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Product with ID " + op.getProduct().getId() + " not found."));
            op.setProduct(product);
            op.setOrder(savedOrder);
        }

        orderRepository.save(savedOrder);
        return orderMapper.orderToOrderDTO(savedOrder);
    }

    @Override
    @Transactional
    public OrderDTO removeOrderById(Integer id)
            throws EntityNotFoundException, IllegalArgumentException {

        validateId(id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with ID " + id + " not found."));
        orderRepository.deleteById(id);

        return orderMapper.orderToOrderDTO(order);
    }

    private void validateId(Integer id)
            throws IllegalArgumentException {

        if(id < 0){
            throw new IllegalArgumentException("Invalid ID: ID should not be negative. Given ID: " + id);
        }
    }
}

package ua.ithillel.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.database.entity.Order;
import ua.ithillel.spring.database.repository.OrderRepository;
import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.OrderDTO;
import ua.ithillel.spring.model.mapper.OrderMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceDefault implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

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
            throws RuntimeException {

        Order order = orderMapper.orderDTOToOrder(orderDTO);
        Order savedOrderOpt = orderRepository.save(order)
                .orElseThrow(() -> new RuntimeException("Failed to save the order: " + orderDTO));

        return orderMapper.orderToOrderDTO(savedOrderOpt);
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

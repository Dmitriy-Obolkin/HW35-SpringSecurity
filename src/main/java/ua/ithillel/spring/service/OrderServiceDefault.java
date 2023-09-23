package ua.ithillel.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public OrderDTO findById(Integer id){

        validateId(id);

        Optional<OrderDTO> orderDTOOpt = orderRepository.findById(id)
                .map(orderMapper::orderToOrderDTO);

        if(orderDTOOpt.isEmpty()){
            throw new EntityNotFoundException("Order with ID " + id + " not found.");
        }

        return orderDTOOpt.get();
    }

    @Override
    public List<OrderDTO> findAll() {

        Optional<List<OrderDTO>> orderDTOsOpt = orderRepository.findAll()
                .map(orders -> orders.stream()
                        .map(orderMapper::orderToOrderDTO)
                        .collect(Collectors.toList()));

        if(orderDTOsOpt.isEmpty()){
            throw new EntityNotFoundException("List Products is empty");
        }

        return orderDTOsOpt.get();
    }

    private void validateId(Integer id){
        if(id < 0){
            throw new IllegalArgumentException("Invalid ID: ID should not be negative. Given ID: " + id);
        }
    }
}

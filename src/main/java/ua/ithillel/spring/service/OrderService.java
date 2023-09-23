package ua.ithillel.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.spring.database.repository.OrderRepository;
import ua.ithillel.spring.model.dto.OrderDTO;
import ua.ithillel.spring.model.mapper.OrderMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public Optional<OrderDTO> findById(Integer id){
        return orderRepository.findById(id)
                .map(orderMapper::orderToOrderDTO);
    }
}

package ua.ithillel.spring.service;

import ua.ithillel.spring.exception.EntityNotFoundException;
import ua.ithillel.spring.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO findById(Integer id) throws IllegalArgumentException, EntityNotFoundException;

    List<OrderDTO> findAll() throws EntityNotFoundException;

    OrderDTO addOrder(OrderDTO orderDTO) throws EntityNotFoundException, RuntimeException;

    OrderDTO removeOrderById(Integer id) throws EntityNotFoundException, IllegalArgumentException;
}

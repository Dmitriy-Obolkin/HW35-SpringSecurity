package ua.ithillel.spring.service;

import ua.ithillel.spring.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO findById(Integer id);

    List<OrderDTO> findAll();
}

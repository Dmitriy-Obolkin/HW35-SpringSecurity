package ua.ithillel.model.mapper;

import org.springframework.stereotype.Component;
import ua.ithillel.model.dto.OrderDTO;
import ua.ithillel.model.dto.ProductDTO;
import ua.ithillel.model.entity.Order;
import ua.ithillel.model.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper implements Mapper<Order, OrderDTO>{
    protected ProductMapper productMapper;

    @Override
    public OrderDTO toDTO(Order entity) {
        if(entity == null){
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(entity.getId());
        orderDTO.setDate(entity.getDate());
        orderDTO.setCost(entity.getCost());
        orderDTO.setProducts(convertProductsToDTOs(entity.getProducts()));
        return null;
    }

    private List<ProductDTO> convertProductsToDTOs(List<Product> products) {
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
}

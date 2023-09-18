package ua.ithillel.model.mapper;

import org.springframework.stereotype.Component;
import ua.ithillel.model.dto.ProductDTO;
import ua.ithillel.model.entity.Product;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO>{

    @Override
    public ProductDTO toDTO(Product entity) {
        if(entity == null){
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(entity.getId());
        productDTO.setName(entity.getName());
        productDTO.setCost(entity.getCost());
        return productDTO;
    }
}

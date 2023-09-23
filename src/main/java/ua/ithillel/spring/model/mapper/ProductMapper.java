package ua.ithillel.spring.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ua.ithillel.spring.database.entity.Product;
import ua.ithillel.spring.model.dto.ProductDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductMapper {

    @Named("productToProductDTO")
    public abstract ProductDTO productToProductDTO(Product product);

    public abstract Product productDTOToProduct(ProductDTO productDTO);
}

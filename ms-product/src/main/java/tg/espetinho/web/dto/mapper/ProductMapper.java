package tg.espetinho.web.dto.mapper;

import org.springframework.stereotype.Component;
import tg.espetinho.entity.Product;
import tg.espetinho.web.dto.*;

@Component
public class ProductMapper {

    public Product toEntity(CreateRequestDTO productRequestDTO) {
        return new Product(
                productRequestDTO.name(),
                productRequestDTO.description(),
                productRequestDTO.price(),
                productRequestDTO.quantity(),
                productRequestDTO.urlImage(),
                productRequestDTO.active()
        );
    }

    public void toEntity(Product product, UpdateRequestDTO requestDTO) {
        product.setName(requestDTO.name());
        product.setActive(requestDTO.active());
        product.setDescription(requestDTO.description());
        product.setPrice(requestDTO.price());
        product.setQuantity(requestDTO.quantity());
        product.setUrlImage(requestDTO.urlImage());
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getProductCode(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getUrlImage(),
                product.getActive()
        );
    }
}

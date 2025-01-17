package tg.espetinho.web.dto.mapper;


import org.springframework.stereotype.Component;
import tg.espetinho.model.CartItem;
import tg.espetinho.web.dto.CartResponseDTO;
import tg.espetinho.web.dto.CreateRequestDTO;
import tg.espetinho.web.dto.UpdateRequestDTO;

@Component
public class CartItemMapper {
    public CartItem toEntity(CreateRequestDTO requestDTO) {
        return new CartItem(
                requestDTO.orderId(),
                requestDTO.productId(),
                requestDTO.quantity(),
                requestDTO.unitPrice(),
                requestDTO.name()
        );
    }

    public void toEntity(CartItem item,UpdateRequestDTO requestDTO){
        item.setId(requestDTO.id());
        item.setQuantity(requestDTO.quantity());
    }

    public CartResponseDTO toResponse(CartItem item) {
        return new CartResponseDTO(
                item.getId(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getName()
        );
    }
}

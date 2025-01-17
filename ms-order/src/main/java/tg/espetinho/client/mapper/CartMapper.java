package tg.espetinho.client.mapper;

import org.springframework.stereotype.Component;
import tg.espetinho.client.dto.OrderItemDTO;
import tg.espetinho.client.dto.UpdateItemDTO;

@Component
public class CartMapper {
    public UpdateItemDTO toUpdateItem(OrderItemDTO itemDTO){
        return new UpdateItemDTO(
                itemDTO.id(),
                itemDTO.quantity()
        );
    }
}

package tg.espetinho.web.dto.mapper;

import org.springframework.stereotype.Component;
import tg.espetinho.entity.Delivery;
import tg.espetinho.entity.DeliveryStatus;
import tg.espetinho.web.dto.CreateRequestDTO;
import tg.espetinho.web.dto.CreateResponseDTO;
import tg.espetinho.web.dto.FindResponseDTO;
import tg.espetinho.web.dto.UpdateRequestDTO;
import tg.espetinho.web.exception.EntityNotFoundException;

@Component
public class DeliveryMapper {

    public Delivery toEntity(CreateRequestDTO requestDTO) {
        return new Delivery(
                requestDTO.orderId(),
                requestDTO.address(),
                requestDTO.deliveryDate(),
                mapToDeliveryStatus(requestDTO.deliveryStatus())
        );
    }

    public void toEntity(Delivery delivery, UpdateRequestDTO requestDTO) {
        delivery.setDeliveryAddress(requestDTO.address());
        delivery.setDeliveryDate(requestDTO.deliveryDate());
        delivery.setDeliveryStatus(DeliveryStatus.valueOf(requestDTO.deliveryStatus()));
    }

    public CreateResponseDTO toResponseDTO(Delivery delivery) {
        return new CreateResponseDTO(
                delivery.getDeliveryAddress(),
                delivery.getDeliveryDate(),
                delivery.getDeliveryStatus().name(),
                delivery.getSecurityCode()
        );
    }

    public FindResponseDTO toFindResponseDTO(Delivery delivery){
        return new FindResponseDTO(
                delivery.getDeliveryAddress(),
                delivery.getDeliveryDate(),
                delivery.getDeliveryStatus().name(),
                delivery.getSecurityCode()
        );
    }

    private DeliveryStatus mapToDeliveryStatus(String status) {
        try {
            return DeliveryStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Invalid delivery status: " + status);
        }
    }
}

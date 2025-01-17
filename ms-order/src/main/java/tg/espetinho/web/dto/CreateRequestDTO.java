package tg.espetinho.web.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import tg.espetinho.client.dto.OrderItemDTO;
import tg.espetinho.entity.PaymentStatus;
import tg.espetinho.entity.PaymentType;

import java.util.List;

public record CreateRequestDTO(
        String customerCPF,
        @Enumerated(EnumType.STRING)
        PaymentType paymentType,
        @Enumerated(EnumType.STRING)
        PaymentStatus paymentStatus,
        @NotNull
        List<OrderItemDTO> items
) {
}

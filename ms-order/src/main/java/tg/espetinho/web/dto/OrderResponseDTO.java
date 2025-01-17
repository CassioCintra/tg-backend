package tg.espetinho.web.dto;

import tg.espetinho.client.dto.OrderItemDTO;
import tg.espetinho.entity.PaymentStatus;
import tg.espetinho.entity.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long codOrder,
        Double amount,
        LocalDateTime orderDate,
        PaymentType paymentType,
        PaymentStatus paymentStatus,
        List<OrderItemDTO> items
) {}

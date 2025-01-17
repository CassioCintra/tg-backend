package tg.espetinho.web.dto;

public record CreateRequestDTO(
        Long orderId,
        Long productId,
        Integer quantity,
        Double unitPrice,
        String name
) {}

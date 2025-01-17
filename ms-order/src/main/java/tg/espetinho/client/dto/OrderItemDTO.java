package tg.espetinho.client.dto;

public record OrderItemDTO(
        Long id,
        Integer quantity,
        Double unitPrice,
        String name
) {
}

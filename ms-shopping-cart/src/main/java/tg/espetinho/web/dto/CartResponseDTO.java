package tg.espetinho.web.dto;

public record CartResponseDTO(
        Long id,
        Integer quantity,
        Double unitPrice,
        String name
) {
}

package tg.espetinho.web.dto;

public record UpdateRequestDTO(
        String cpf,
        String name,
        String telephone
) {
}

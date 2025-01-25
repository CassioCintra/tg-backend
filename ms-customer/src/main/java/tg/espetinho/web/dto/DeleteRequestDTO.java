package tg.espetinho.web.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteRequestDTO(
        @NotNull(message = "The CPF cannot be null")
        @NotNull(message = "The CPF cannot be empty.")
        String cpf
) {
}

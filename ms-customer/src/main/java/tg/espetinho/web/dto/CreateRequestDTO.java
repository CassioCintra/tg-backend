package tg.espetinho.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRequestDTO(
        @NotNull
        @Size(min = 11, max = 11, message = "The CPF must be exactly 11 characters long.")
        String name,
        @NotEmpty(message = "The name cannot be empty.")
        String cpf,
        String telephone
) {
}

package tg.espetinho.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateRequestDTO(
        @NotNull(message = "The CPF cannot be null")
        @NotEmpty(message = "The CPF cannot be empty")
        @NotBlank(message = "The CPF cannot be blank")
        String cpf,
        String name,
        String telephone
) {
}

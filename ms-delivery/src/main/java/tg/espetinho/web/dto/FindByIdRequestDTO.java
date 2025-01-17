package tg.espetinho.web.dto;

import jakarta.validation.constraints.NotNull;

public record FindByIdRequestDTO(
        @NotNull
        Long id
) {
}

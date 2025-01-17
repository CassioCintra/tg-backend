package tg.espetinho.web.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteRequestDTO(
        @NotNull
        Long id
) {
}

package tg.espetinho.web.dto;

import jakarta.validation.constraints.NotNull;

public record FindByNameRequestDTO(
        @NotNull
        String name
) {
}

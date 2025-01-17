package tg.espetinho.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record FindByOrderIdDTO(
        @NotNull
        @JsonProperty("order-id")
        Long orderId
) {
}

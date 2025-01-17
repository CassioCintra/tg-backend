package tg.espetinho.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateRequestDTO(
        @JsonProperty("order_id")
        @NotNull
        Long orderId,
        @JsonProperty("address")
        @NotBlank
        String address,
        @JsonProperty("delivery_date")
        LocalDateTime deliveryDate,
        @JsonProperty("delivery_status")
        @NotBlank
        String deliveryStatus
) {}

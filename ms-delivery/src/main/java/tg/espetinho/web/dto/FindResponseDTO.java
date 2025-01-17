package tg.espetinho.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record FindResponseDTO(
        @JsonProperty("address")
        String address,
        @JsonProperty("delivery_date")
        LocalDateTime deliveryDate,
        @JsonProperty("delivery_status")
        String deliveryStatus,
        @JsonProperty("delivery_code")
        String securityCode
) {
}

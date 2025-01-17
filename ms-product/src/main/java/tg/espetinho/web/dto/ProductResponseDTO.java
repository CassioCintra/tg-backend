package tg.espetinho.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponseDTO(
        @JsonProperty("product_id")
        Long codeProduct,
        String name,
        String description,
        Double price,
        Integer quantity,
        @JsonProperty("url_image")
        String urlImages,
        Boolean active
) {}

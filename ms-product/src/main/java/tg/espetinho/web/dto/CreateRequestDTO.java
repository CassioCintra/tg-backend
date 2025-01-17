package tg.espetinho.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

public record CreateRequestDTO(
        @NotBlank(message = "O nome não pode estar vazio.")
        String name,
        String description,
        @NotNull(message="O preço não pode ser nulo.")
        @Min(value = 0, message = "O preço não pode ser negativo.")
        Double price,
        @NotNull(message = "A quantidade não pode ser nula.")
        @Min(value = 1, message = "Deve haver ao menos 1.")
        Integer quantity,
        @JsonProperty("url_image")
        String urlImage,
        Boolean active
){}

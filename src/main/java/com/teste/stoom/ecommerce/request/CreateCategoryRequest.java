package com.teste.stoom.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCategoryRequest {

    @NotBlank(message = "O nome da categoria é obrigatório.")
    private String name;

    private String description;

    private Boolean isActive = true;
}

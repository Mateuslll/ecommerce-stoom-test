package com.teste.stoom.ecommerce.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequest {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String name;
    @NotBlank(message = "A descrição do produto é obrigatória.")
    private String description;
    private Boolean isActive;
}
package com.teste.stoom.ecommerce.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBrandRequest {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String name;
    private String logoUrl;
    private Boolean isActive;
}

package com.teste.stoom.ecommerce.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBrandRequest {

    @NotBlank(message = "O nome da marca é obrigatório.")
    private String name;

    private String logoUrl;

    private Boolean isActive = true;
}

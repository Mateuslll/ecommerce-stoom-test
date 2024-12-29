package com.teste.stoom.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBrandRequest {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String name;
    private String logoUrl;
    private Boolean isActive;
}

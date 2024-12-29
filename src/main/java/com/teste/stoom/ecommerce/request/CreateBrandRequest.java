package com.teste.stoom.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBrandRequest {

    @NotBlank(message = "O nome da marca é obrigatório.")
    private String name;

    private String logoUrl;

    private Boolean isActive = true;
}

package com.teste.stoom.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProductRequest {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String name;

    @NotBlank(message = "A descrição do produto é obrigatória.")
    private String description;

    @NotNull(message = "O preço do produto é obrigatório.")
    @Positive(message = "O preço deve ser um valor positivo.")
    private Double price;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoryId;

    @NotBlank(message = "A marca é obrigatória.")
    private String brandId;

    private List<String> images;
}

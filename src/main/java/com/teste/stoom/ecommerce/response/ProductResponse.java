package com.teste.stoom.ecommerce.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String categoryId;
    private String brandId;
    private Boolean isActive;
    private String images;
    private String createdAt;
    private String updatedAt;
}
package com.teste.stoom.ecommerce.service.mapper;

import com.teste.stoom.ecommerce.model.entity.Brand;
import com.teste.stoom.ecommerce.model.entity.Category;
import com.teste.stoom.ecommerce.model.entity.Product;
import com.teste.stoom.ecommerce.request.CreateProductRequest;
import com.teste.stoom.ecommerce.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductMapper {

        public static Product fromRequestToEntity(CreateProductRequest request) {
            if (Objects.isNull(request)) {
                return null;
            }

            Category category = Category.builder().id(request.getCategoryId()).build();
            Brand brand = Brand.builder().id(request.getBrandId()).build();

            return Product.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(request.getPrice())
                    .category(category)
                    .brand(brand)
                    .isActive(true)
                    .images(request.getImages())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
        }

    public static ProductResponse fromEntityToResponse(Product entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .categoryId(entity.getCategory() != null ? entity.getCategory().getId() : null)
                .brandId(entity.getBrand() != null ? entity.getBrand().getId() : null)
                .isActive(entity.getIsActive())
                .images(entity.getImages() != null ? entity.getImages().toString() : null)
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().toString() : null)
                .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().toString() : null)
                .build();
    }
}

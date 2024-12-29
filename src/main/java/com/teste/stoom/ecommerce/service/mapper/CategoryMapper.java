package com.teste.stoom.ecommerce.service.mapper;

import com.teste.stoom.ecommerce.model.entity.Category;
import com.teste.stoom.ecommerce.request.CreateCategoryRequest;
import com.teste.stoom.ecommerce.response.CategoryResponse;

public class CategoryMapper {

    public static Category fromRequestToEntity(CreateCategoryRequest request) {
        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .isActive(request.getIsActive())
                .build();
    }

    public static CategoryResponse fromEntityToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .isActive(category.getIsActive())
                .build();
    }
}

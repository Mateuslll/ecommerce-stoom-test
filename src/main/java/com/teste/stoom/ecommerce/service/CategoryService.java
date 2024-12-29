package com.teste.stoom.ecommerce.service;

import com.teste.stoom.ecommerce.model.entity.Category;
import com.teste.stoom.ecommerce.request.CreateCategoryRequest;
import com.teste.stoom.ecommerce.request.UpdateCategoryRequest;
import com.teste.stoom.ecommerce.response.CategoryResponse;

import java.util.List;


public interface CategoryService {

    CategoryResponse create(CreateCategoryRequest request);

    CategoryResponse update(String id, UpdateCategoryRequest request);

    CategoryResponse deactivateCategory(String id);

    CategoryResponse getCategoryById(String id);

}

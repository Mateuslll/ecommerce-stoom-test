package com.teste.stoom.ecommerce.service.impl;

import com.teste.stoom.ecommerce.exception.CategoryAlreadyDeactivatedExpection;
import com.teste.stoom.ecommerce.exception.CategoryAlreadyExistsException;
import com.teste.stoom.ecommerce.exception.CategoryNotFoundException;
import com.teste.stoom.ecommerce.model.entity.Category;
import com.teste.stoom.ecommerce.repository.CategoryRepository;
import com.teste.stoom.ecommerce.request.CreateCategoryRequest;
import com.teste.stoom.ecommerce.request.UpdateCategoryRequest;
import com.teste.stoom.ecommerce.response.CategoryResponse;
import com.teste.stoom.ecommerce.service.CategoryService;
import com.teste.stoom.ecommerce.service.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {
        if(Objects.isNull(request)) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        categoryRepository.findCategoryByName(request.getName()).ifPresent(category -> {
            throw new CategoryAlreadyExistsException(request.getName());
        });
        Category category = CategoryMapper.fromRequestToEntity(request);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.fromEntityToResponse(savedCategory);
    }

    @Override
    public CategoryResponse update(String id, UpdateCategoryRequest request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        existingCategory.setName(request.getName());
        existingCategory.setDescription(request.getDescription());
        existingCategory.setIsActive(request.getIsActive());

        Category updatedCategory = categoryRepository.save(existingCategory);

        return CategoryMapper.fromEntityToResponse(updatedCategory);
    }

    @Override
    public CategoryResponse deactivateCategory(String id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        if(!existingCategory.getIsActive()) {
            throw new CategoryAlreadyDeactivatedExpection(id);
        }
        existingCategory.setIsActive(false);
        Category updatedCategory = categoryRepository.save(existingCategory);
        return CategoryMapper.fromEntityToResponse(updatedCategory);
    }

    @Override
    public CategoryResponse getCategoryById(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            return CategoryMapper.fromEntityToResponse(categoryOptional.get());
        } else {
            throw new CategoryNotFoundException(id);
        }
    }
}

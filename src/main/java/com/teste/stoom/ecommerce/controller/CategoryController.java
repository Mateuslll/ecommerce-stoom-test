package com.teste.stoom.ecommerce.controller;

import com.teste.stoom.ecommerce.exception.CategoryAlreadyDeactivatedExpection;
import com.teste.stoom.ecommerce.exception.CategoryAlreadyExistsException;
import com.teste.stoom.ecommerce.exception.CategoryNotFoundException;
import com.teste.stoom.ecommerce.request.CreateCategoryRequest;
import com.teste.stoom.ecommerce.request.UpdateCategoryRequest;
import com.teste.stoom.ecommerce.response.CategoryResponse;
import com.teste.stoom.ecommerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CreateCategoryRequest request) {
        CategoryResponse categoryResponse = categoryService.create(request);
        return ResponseEntity.status(201).body(categoryResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable String id) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse); // Retorna 200 OK com os dados da categoria
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String id,
                                                           @RequestBody UpdateCategoryRequest request) {

        CategoryResponse categoryResponse = categoryService.update(id, request);
        return ResponseEntity.ok(categoryResponse);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CategoryResponse> deactivateCategory(@PathVariable String id) {
        CategoryResponse categoryResponse = categoryService.deactivateCategory(id);
        return ResponseEntity.ok(categoryResponse);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryAlreadyDeactivatedExpection.class)
    public ResponseEntity<String> handleCategoryAlreadyDeactivatedExpection(CategoryAlreadyDeactivatedExpection ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<String> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}

package com.teste.stoom.ecommerce.controller;


import com.teste.stoom.ecommerce.exception.BrandNotFoundException;
import com.teste.stoom.ecommerce.exception.CategoryNotFoundException;
import com.teste.stoom.ecommerce.exception.ProductAlreadyDeactivatedExpection;
import com.teste.stoom.ecommerce.exception.ProductAlreadyExistsException;
import com.teste.stoom.ecommerce.exception.ProductNotFoundException;
import com.teste.stoom.ecommerce.request.CreateProductRequest;
import com.teste.stoom.ecommerce.request.UpdateProductRequest;
import com.teste.stoom.ecommerce.response.ProductResponse;
import com.teste.stoom.ecommerce.service.ProductService;
import com.teste.stoom.ecommerce.util.PageFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid CreateProductRequest request) {
        ProductResponse productResponse = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        ProductResponse productResponse = productService.getProductById(id);
        if (productResponse != null) {
            return ResponseEntity.ok(productResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(PageFilter pageFilter) {
        Page<ProductResponse> products = productService.getAllProducts(pageFilter);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<ProductResponse>> getProductsByBrand(
            @PathVariable String brandId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        List<ProductResponse> productResponses = productService.getProductsByBrand(brandId, page, pageSize);
        return ResponseEntity.ok(productResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String id,
            @RequestBody UpdateProductRequest request) {

        ProductResponse updatedProduct = productService.update(id, request);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ProductResponse> deactivateProduct(@PathVariable String id) {
        ProductResponse productResponse = productService.deactivateProduct(id);
        return ResponseEntity.ok(productResponse);
    }

    @ExceptionHandler(ProductAlreadyDeactivatedExpection.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductAlreadyDeactivatedExpection ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<String> handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<String> handleBrandNotFoundException(BrandNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

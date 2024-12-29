package com.teste.stoom.ecommerce.controller;

import com.teste.stoom.ecommerce.exception.BrandAlreadyDeactivatedExpection;
import com.teste.stoom.ecommerce.exception.BrandAlreadyExistsException;
import com.teste.stoom.ecommerce.exception.BrandNotFoundException;
import com.teste.stoom.ecommerce.request.CreateBrandRequest;
import com.teste.stoom.ecommerce.request.UpdateBrandRequest;
import com.teste.stoom.ecommerce.response.BrandResponse;
import com.teste.stoom.ecommerce.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@RequestBody CreateBrandRequest request) {
        BrandResponse brandResponse = brandService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable String id,
                                                     @RequestBody UpdateBrandRequest request) {
        BrandResponse brandResponse = brandService.update(id, request);
        return ResponseEntity.ok(brandResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable String id) {
        BrandResponse brandResponse = brandService.getBrandById(id);
        return ResponseEntity.ok(brandResponse);
    }
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<BrandResponse> deactivateBrand(@PathVariable String id) {
        BrandResponse brandResponse = brandService.deactivateBrand(id);
        return ResponseEntity.ok(brandResponse);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<String> handleBrandNotFoundException(BrandNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(BrandAlreadyDeactivatedExpection.class)
    public ResponseEntity<String> handleBrandAlreadyDeactivatedExpection(BrandAlreadyDeactivatedExpection ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<String> handleBrandAlreadyExistsException(BrandAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}

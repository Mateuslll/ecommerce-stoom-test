package com.teste.stoom.ecommerce.service.impl;

import com.teste.stoom.ecommerce.exception.BrandNotFoundException;
import com.teste.stoom.ecommerce.exception.CategoryNotFoundException;
import com.teste.stoom.ecommerce.exception.ProductAlreadyExistsException;
import com.teste.stoom.ecommerce.exception.ProductNotFoundException;
import com.teste.stoom.ecommerce.model.entity.Brand;
import com.teste.stoom.ecommerce.model.entity.Category;
import com.teste.stoom.ecommerce.model.entity.Product;
import com.teste.stoom.ecommerce.repository.BrandRepository;
import com.teste.stoom.ecommerce.repository.CategoryRepository;
import com.teste.stoom.ecommerce.repository.ProductRepository;
import com.teste.stoom.ecommerce.request.CreateProductRequest;
import com.teste.stoom.ecommerce.request.UpdateProductRequest;
import com.teste.stoom.ecommerce.response.ProductResponse;
import com.teste.stoom.ecommerce.service.ProductService;
import com.teste.stoom.ecommerce.service.mapper.ProductMapper;
import com.teste.stoom.ecommerce.util.PageFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponse create(CreateProductRequest request) {
        if(Objects.isNull(request)) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        productRepository.findProductByName(request.getName()).ifPresent(product -> {
            throw new ProductAlreadyExistsException(request.getName());
        });
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new BrandNotFoundException(request.getBrandId()));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(request.getCategoryId()));

        Product product = ProductMapper.fromRequestToEntity(request);
        product.setBrand(brand);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.fromEntityToResponse(savedProduct);
    }

    @Override
    public ProductResponse update(String id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setIsActive(true);
        product.setPrice(request.getPrice());
        product.setImages(request.getImages());

        if(request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(request.getCategoryId()));
            product.setCategory(category);
        }
        if(request.getBrandId() != null) {
            Brand brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new BrandNotFoundException(request.getBrandId()));
            product.setBrand(brand);
        }

        product.setUpdatedAt(LocalDateTime.now());

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.fromEntityToResponse(updatedProduct);
    }

    @Override
    public ProductResponse getProductById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return ProductMapper.fromEntityToResponse(product);
        } else {
            return null;
        }
    }

    @Override
    public List<ProductResponse> getProductsByBrand(String brandId, int page, int pageSize) {
        if(Objects.isNull(brandId)) {
            throw new BrandNotFoundException(null);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Product> productsPage = productRepository.findByBrandIdAndIsActiveTrue(brandId, pageRequest);

        return productsPage.stream()
                .map(ProductMapper::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse deactivateProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        if(!product.getIsActive()) {
            throw new ProductAlreadyExistsException(id);
        }
        product.setIsActive(false);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.fromEntityToResponse(updatedProduct);
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageFilter pageFilter) {
        PageRequest pageRequest = PageRequest.of(pageFilter.getPage(), pageFilter.getPageSize());

        if (pageFilter.getSearch() != null && !pageFilter.getSearch().isEmpty()) {
            Page<Product> products = productRepository.findBySearchFields(pageFilter.getSearch(), pageRequest);
            return products.map(ProductMapper::fromEntityToResponse);
        } else {
            Page<Product> products = productRepository.findByIsActiveTrue(pageRequest);
            return products.map(ProductMapper::fromEntityToResponse);
        }
    }

}

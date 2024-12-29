package com.teste.stoom.ecommerce.service;



import com.teste.stoom.ecommerce.request.CreateProductRequest;
import com.teste.stoom.ecommerce.request.UpdateProductRequest;
import com.teste.stoom.ecommerce.response.ProductResponse;
import com.teste.stoom.ecommerce.util.PageFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponse create(CreateProductRequest request);

    ProductResponse update (String id, UpdateProductRequest request);

    List<ProductResponse> getProductsByBrand (String brandId, int page, int pageSize);

    ProductResponse deactivateProduct(String id);

    ProductResponse getProductById(String id);

    Page<ProductResponse> getAllProducts(PageFilter pageFilter);

}

package com.teste.stoom.ecommerce.service;

import com.teste.stoom.ecommerce.request.CreateBrandRequest;
import com.teste.stoom.ecommerce.request.UpdateBrandRequest;
import com.teste.stoom.ecommerce.response.BrandResponse;

public interface BrandService {
    BrandResponse create (CreateBrandRequest request);

    BrandResponse update(String id, UpdateBrandRequest request);

    BrandResponse deactivateBrand(String id);

    BrandResponse getBrandById (String id);
}

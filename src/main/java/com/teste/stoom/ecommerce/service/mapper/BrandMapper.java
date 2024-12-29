package com.teste.stoom.ecommerce.service.mapper;

import com.teste.stoom.ecommerce.model.entity.Brand;
import com.teste.stoom.ecommerce.request.CreateBrandRequest;
import com.teste.stoom.ecommerce.response.BrandResponse;

public class BrandMapper {

    public static Brand fromRequestToEntity(CreateBrandRequest request) {
        return Brand.builder()
                .name(request.getName())
                .logoUrl(request.getLogoUrl())
                .isActive(request.getIsActive())
                .build();
    }

    public static BrandResponse fromEntityToResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .logoUrl(brand.getLogoUrl())
                .isActive(brand.getIsActive())
                .build();
    }
}

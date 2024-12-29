package com.teste.stoom.ecommerce.service.impl;

import com.teste.stoom.ecommerce.exception.BrandAlreadyDeactivatedExpection;
import com.teste.stoom.ecommerce.exception.BrandAlreadyExistsException;
import com.teste.stoom.ecommerce.exception.BrandNotFoundException;
import com.teste.stoom.ecommerce.model.entity.Brand;
import com.teste.stoom.ecommerce.repository.BrandRepository;
import com.teste.stoom.ecommerce.request.CreateBrandRequest;
import com.teste.stoom.ecommerce.request.UpdateBrandRequest;
import com.teste.stoom.ecommerce.response.BrandResponse;
import com.teste.stoom.ecommerce.service.BrandService;
import com.teste.stoom.ecommerce.service.mapper.BrandMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandResponse create(CreateBrandRequest request) {
        brandRepository.findBrandByName(request.getName()).ifPresent(brand -> {
            throw new BrandAlreadyExistsException(request.getName());
        });
        Brand brand = BrandMapper.fromRequestToEntity(request);
        Brand savedBrand = brandRepository.save(brand);
        return BrandMapper.fromEntityToResponse(savedBrand);
    }
    @Override
    public BrandResponse update(String id, UpdateBrandRequest request) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        existingBrand.setName(request.getName());
        existingBrand.setLogoUrl(request.getLogoUrl());
        existingBrand.setIsActive(request.getIsActive());

        Brand updatedBrand = brandRepository.save(existingBrand);

        return BrandMapper.fromEntityToResponse(updatedBrand);
    }
    @Override
    public BrandResponse deactivateBrand(String id) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        if(!existingBrand.getIsActive()) {
            throw new BrandAlreadyDeactivatedExpection(id);
        }
        existingBrand.setIsActive(false);
        Brand updatedBrand = brandRepository.save(existingBrand);
        return BrandMapper.fromEntityToResponse(updatedBrand);
    }

    @Override
    public BrandResponse getBrandById(String id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            return BrandMapper.fromEntityToResponse(brandOptional.get());
        } else {
            throw new BrandNotFoundException(id);
        }
    }
}

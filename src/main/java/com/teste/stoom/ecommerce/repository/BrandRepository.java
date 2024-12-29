package com.teste.stoom.ecommerce.repository;


import com.teste.stoom.ecommerce.model.entity.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
    Optional<Object> findBrandByName(String name);
}

package com.teste.stoom.ecommerce.repository;
import com.teste.stoom.ecommerce.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Page<Product> findByIsActiveTrue(Pageable pageable);

    @Query("{ $or: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'category._id': { $regex: ?0, $options: 'i' } }, { 'brand._id': { $regex: ?0, $options: 'i' } } ] }")
    Page<Product> findBySearchFields(String searchTerm, Pageable pageable);

    Optional<Object> findProductByName(String name);
    @Query("{ 'brand.id': ?0, 'isActive': true }")
    Page<Product> findByBrandIdAndIsActiveTrue(String brandId, Pageable pageable);

}

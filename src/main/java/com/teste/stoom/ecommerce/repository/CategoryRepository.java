package com.teste.stoom.ecommerce.repository;

import com.teste.stoom.ecommerce.model.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Object> findCategoryByName(String name);
}

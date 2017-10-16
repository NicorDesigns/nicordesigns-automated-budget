package com.nicordesigns.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface CategoryRepository extends MongoRepository<Category, String> {

    Collection<Category> findByCategoryName(String categoryName);

    Collection<Category> findByAccountsUsername(String username);

    //Collection<Category> findBySubCategoriesMainCategory(MainCategory mainCategory);
}
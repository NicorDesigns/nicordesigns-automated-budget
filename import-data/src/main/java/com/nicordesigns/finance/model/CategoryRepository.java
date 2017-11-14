package com.nicordesigns.finance.model;

import org.springframework.data.repository.Repository;

import java.util.Collection;

public interface CategoryRepository extends Repository<Category, String> {

    Collection<Category> findByCategoryName(String categoryName);


}
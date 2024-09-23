package com.ecom.service;

import com.ecom.payload.CategoryDTO;
import com.ecom.payload.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}

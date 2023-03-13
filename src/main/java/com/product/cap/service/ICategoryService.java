package com.product.cap.service;

import com.product.cap.dto.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {

    //create new category
    public CategoryDTO createCategory(CategoryDTO category);

    //update the category
    public CategoryDTO updateCategory(CategoryDTO category, UUID id);

    //select all categories
    public List<CategoryDTO> getAllCategories();

    //select category by id
    public CategoryDTO getCategoryById(UUID id);

    //delete the category
    public void deleteCategory(UUID id);

    public List<CategoryDTO> categoryPagination(int NumberOfPage, int Size);

    public Long countCategories();
}

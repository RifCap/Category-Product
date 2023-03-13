package com.product.cap.service.Impl;

import com.product.cap.dao.CategoryRepository;
import com.product.cap.dto.CategoryDTO;
import com.product.cap.dto.mapper.CategoryMapper;
import com.product.cap.entity.CategoryEntity;
import com.product.cap.exception.EntityAlreadyExistsException;
import com.product.cap.exception.NoEntityExistsException;
import com.product.cap.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    // create new category
    public CategoryDTO createCategory(CategoryDTO category) {
        //check category name exists in the database or not
        if(categoryRepository.existsByName(category.getNom())) {
            throw new EntityAlreadyExistsException("The category name '"+category.getNom()+"' already exists");
        }

        return categoryMapper.toCategoryDTO(categoryRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    //update category
    public CategoryDTO updateCategory(CategoryDTO category, UUID id) {
        //get old category by id
        CategoryEntity oldCategory = categoryRepository.findById(id)
                //check if the field is not empty
                .orElseThrow(() -> new NoEntityExistsException("No Category found with the identifier "+id));

        //check if old name equals new name
        if(oldCategory.getNom().equals(category.getNom())) {
            throw new EntityAlreadyExistsException("The category name "+category.getNom()+" already exists");
        }

        oldCategory.setNom(category.getNom());
        oldCategory.setDescription(category.getDescription());
        return categoryMapper.toCategoryDTO(categoryRepository.save(oldCategory));
    }

    @Override
    //select all categories
    public List<CategoryDTO> getAllCategories() {
        return categoryMapper.toCategoryDTOs(categoryRepository.findAll());
    }

    @Override
    //select category by id
    public CategoryDTO getCategoryById(UUID id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new
                NoEntityExistsException("No Category found with the identifier "+id));
        return categoryMapper.toCategoryDTO(categoryEntity);
    }

    @Override
    //delete category by id
    public void deleteCategory(UUID id) {
        categoryRepository.findById(id).orElseThrow(() -> new NoEntityExistsException("No Category found with the identifier "+id));
        categoryRepository.deleteById(id);
    }

    @Override
    //Category pagination
    public List<CategoryDTO> categoryPagination(int numberOfPage, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numberOfPage, pageSize);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageRequest);
        return categoryMapper.toCategoryDTOs(categoryPage.toList());
    }

    @Override
    public Long countCategories() {
        return categoryRepository.count();
    }
}

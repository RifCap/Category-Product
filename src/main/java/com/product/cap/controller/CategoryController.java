package com.product.cap.controller;

import com.product.cap.dto.CategoryDTO;
import com.product.cap.exception.EntityAlreadyExistsException;
import com.product.cap.exception.NoEntityExistsException;
import com.product.cap.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    //Create Category
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO category) {
        try {
            logger.info("Request to add a new category "+category);
            CategoryDTO categoryDTO = categoryService.createCategory(category);
            return ResponseEntity.ok(categoryDTO);
        }catch(EntityAlreadyExistsException e) {
            //Exception category exists
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    //Update Category
    public ResponseEntity<CategoryDTO> setCategory(@Valid @RequestBody CategoryDTO category, @PathVariable("id") UUID id) {
        try {
            logger.info("Request to update a category "+category);
            CategoryDTO categoryDTO = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(categoryDTO);
        }catch(NoEntityExistsException e) {
            //First exception category not exists
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }catch(EntityAlreadyExistsException e) {
            //Second exception category already exists
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @DeleteMapping("/delete/{id}")
    //Delete Category
    public void removeCategory(@PathVariable("id") UUID id) {
        try {
            logger.info("Request to delete category ID: "+id);
            categoryService.deleteCategory(id);
        }catch(NoEntityExistsException e) {
            //Exception category not exists
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @GetMapping("/all")
    //Select Categories
    public List<CategoryDTO> Categories() {
        logger.info("Request to select all categories");
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    //Select Categories
    public ResponseEntity<CategoryDTO> Category(@PathVariable("id") UUID id) {
        try {
            logger.info("Request to find a category ID: "+id);
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        }catch(NoEntityExistsException e) {
            //exception category not exists
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @GetMapping("/pagination/{page}/{size}")
    //Category Pagination
    public List<CategoryDTO> paginationCategories(@PathVariable("page") int page, @PathVariable("size") int size) {
        logger.info("Request to select row of categories by pages");
        return categoryService.categoryPagination(page, size);
    }

    @GetMapping("/count")
    public Long countCategory() {
        logger.info("Request to count categories");
        return categoryService.countCategories();
    }
}

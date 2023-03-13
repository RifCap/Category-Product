package com.product.cap.dao;

import com.product.cap.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID>, PagingAndSortingRepository<CategoryEntity, UUID> {

    //create a new request to check if the category name exists or not
    @Query("select count(C)>0 from CategoryEntity C where C.Nom = :name")
    public boolean existsByName(@Param("name") String name);



}

package com.product.cap.dao;

import com.product.cap.entity.CategoryEntity;
import com.product.cap.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>, PagingAndSortingRepository<ProductEntity, UUID> {
}

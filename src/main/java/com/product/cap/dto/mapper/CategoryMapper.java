package com.product.cap.dto.mapper;

import com.product.cap.dto.CategoryDTO;
import com.product.cap.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(CategoryEntity categoryEntity);
    CategoryEntity toCategoryEntity(CategoryDTO categoryDTO);
    List<CategoryDTO> toCategoryDTOs(List<CategoryEntity> categoryEntities);

    List<CategoryEntity> toCategoryEntities(List<CategoryDTO> categoryDTOs);
}

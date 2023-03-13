package com.product.cap.dto.mapper;

import com.product.cap.dto.ProductDTO;
import com.product.cap.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(ProductEntity productEntity);
    ProductEntity toProductEntity(ProductDTO productDTO);
    List<ProductDTO> toProductDTOs(List<ProductEntity> productEntities);
    List<ProductEntity> toProductEntities(List<ProductDTO> productDTOs);
}

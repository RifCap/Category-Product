package com.product.cap.service;

import com.product.cap.dto.ProductDTO;
import com.product.cap.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    //create new product
    public ProductDTO createProduct(ProductDTO product);

    //update the product
    public ProductDTO updateProduct(ProductDTO product, UUID id);

    //select all products
    public List<ProductDTO> getAllProduct();

    //select product by id
    public ProductDTO getProductById(UUID id);

    //delete the product
    public void deleteProduct(UUID id);

    public List<ProductDTO> productPagination(int NumberOfPage, int Size);

}

package com.product.cap.service.Impl;

import com.product.cap.dao.CategoryRepository;
import com.product.cap.dao.ProductRepository;
import com.product.cap.dto.ProductDTO;
import com.product.cap.dto.mapper.CategoryMapper;
import com.product.cap.dto.mapper.ProductMapper;
import com.product.cap.entity.ProductEntity;
import com.product.cap.exception.NoEntityExistsException;
import com.product.cap.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ProductServiceImp implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    //add new product
    public ProductDTO createProduct(ProductDTO product) {
        if(categoryRepository.existsById(product.getCategories().getId())) {
            return productMapper.toProductDTO(productRepository.save(productMapper.toProductEntity(product)));
        }else {
            throw new NoEntityExistsException("Category not exists");
        }
    }

    @Override
    //update product by id
    public ProductDTO updateProduct(ProductDTO product, UUID id) {
        //check category exists or not
        if(categoryRepository.existsById(product.getCategories().getId())) {
            //get old product
            ProductEntity oldProduct = productRepository.findById(id)
                    //start the exception when the product is not found
                    .orElseThrow(() -> new NoEntityExistsException("No product found with the identifier "+id));
            //change old values to new values
            oldProduct.setNom(product.getNom());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setPrix_vente(product.getPrix_vente());
            oldProduct.setPrix_achat(product.getPrix_achat());
            oldProduct.setStock(product.getStock());
            oldProduct.setCategories(categoryMapper.toCategoryEntity(product.getCategories()));
            return productMapper.toProductDTO(productRepository.save(oldProduct));
        }else {
            throw new NoEntityExistsException("Category not exists");
        }
    }

    @Override
    //select all products from database
    public List<ProductDTO> getAllProduct() {
        return productMapper.toProductDTOs(productRepository.findAll());
    }

    @Override
    //select product by id
    public ProductDTO getProductById(UUID id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NoEntityExistsException("No product found with the identifier "+id));
        return productMapper.toProductDTO(productEntity);
    }

    @Override
    //delete product by id
    public void deleteProduct(UUID id) {
        productRepository.findById(id).orElseThrow(() -> new NoEntityExistsException("No product found with the identifier "+id));
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> productPagination(int numberOfPages, int pageSize) {

        PageRequest pageRequest = PageRequest.of(numberOfPages, pageSize);
        Page<ProductEntity> productPage = productRepository.findAll(pageRequest);

        return productMapper.toProductDTOs(productPage.toList());
    }
}

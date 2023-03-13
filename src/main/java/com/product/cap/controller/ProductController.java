package com.product.cap.controller;

import com.product.cap.dto.ProductDTO;
import com.product.cap.exception.NoEntityExistsException;
import com.product.cap.service.IProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    //Create Product
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO product) {
        try {
            logger.info("Request to add a new product "+product);
            ProductDTO productDTO = productService.createProduct(product);
            return ResponseEntity.ok(productDTO);
        }catch(NoEntityExistsException e) {
            //exception category not exists
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }

    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    //Update Product
    public ResponseEntity<ProductDTO> setProduct(@Valid @RequestBody ProductDTO product, @PathVariable("id") UUID id) {
        try {
            logger.info("Request to update a product "+product);
            ProductDTO productDTO = productService.updateProduct(product, id);
            return ResponseEntity.ok(productDTO);
        }catch(NoEntityExistsException e) {
            //exception product not exists
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @DeleteMapping("/delete/{id}")
    //Delete Product
    public void removeProduct(@PathVariable("id") UUID id) {
        try {
            logger.info("Request to delete product ID: "+id);
            productService.deleteProduct(id);
        }catch(NoEntityExistsException e) {
            //Exception product not exists
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @GetMapping("/all")
    //Select Product
    public List<ProductDTO> Products() {
        logger.info("Request to select all products");
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    //Select one product
    public ResponseEntity<ProductDTO> Product(@PathVariable("id") UUID id) {
        try {
            logger.info("Request to find a product ID: "+id);
            return ResponseEntity.ok(productService.getProductById(id));
        }catch(NoEntityExistsException e) {
            //exception product not exists
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @GetMapping("/pagination/{page}/{size}")
    //Product Pagination
    public List<ProductDTO> paginationProducts(@PathVariable("page") int page, @PathVariable("size") int size) {
        logger.info("Request to select row of products by pages");
        return productService.productPagination(page, size);
    }
}

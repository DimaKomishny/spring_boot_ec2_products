package com.dmytro.komyshnyi.ec2.controller;

import com.dmytro.komyshnyi.ec2.dto.ProductDto;
import com.dmytro.komyshnyi.ec2.facade.ProductFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ProductController {

    private ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable UUID id) {
        return productFacade.findById(id);
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return productFacade.getAll();
    }

    @PostMapping("/product")
    @Transactional
    public ResponseEntity<Map<String, UUID>> saveProduct(@RequestBody ProductDto product) {
        return productFacade.save(product);
    }

    @DeleteMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable UUID id) {
        productFacade.delete(id);
    }
}

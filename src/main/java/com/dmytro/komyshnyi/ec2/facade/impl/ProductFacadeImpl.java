package com.dmytro.komyshnyi.ec2.facade.impl;

import com.dmytro.komyshnyi.ec2.converter.ProductMapper;
import com.dmytro.komyshnyi.ec2.dto.ProductDto;
import com.dmytro.komyshnyi.ec2.entity.Product;
import com.dmytro.komyshnyi.ec2.facade.ProductFacade;
import com.dmytro.komyshnyi.ec2.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductFacadeImpl implements ProductFacade {

    ProductService productService;
    ProductMapper productMapper;

    public ProductFacadeImpl(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> getAll() {
        return productService.getAll().stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(UUID id) {
        Product product = productService.findById(id);
        return productMapper.toProductDto(product);
    }

    @Override
    public ResponseEntity<Map<String, UUID>> save(ProductDto productDto) {
        UUID id = productService.save(productMapper.toProduct(productDto));
        return new ResponseEntity<>(Map.of("id", id), HttpStatus.CREATED);
    }

    @Override
    public void delete(UUID id) {
        productService.delete(id);
    }
}

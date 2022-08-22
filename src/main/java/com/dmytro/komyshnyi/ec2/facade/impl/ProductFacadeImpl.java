package com.dmytro.komyshnyi.ec2.facade.impl;

import com.dmytro.komyshnyi.ec2.converter.ProductMapper;
import com.dmytro.komyshnyi.ec2.dto.ProductDto;
import com.dmytro.komyshnyi.ec2.entity.Product;
import com.dmytro.komyshnyi.ec2.facade.ProductFacade;
import com.dmytro.komyshnyi.ec2.service.ProductService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ProductFacadeImpl implements ProductFacade {

    ProductService productService;
    ProductMapper productMapper;
    AtomicLong totalPrice;

    public ProductFacadeImpl(ProductService productService,
                             ProductMapper productMapper,
                             MeterRegistry meterRegistry) {
        this.productService = productService;
        this.productMapper = productMapper;
        totalPrice = new AtomicLong(0);
        meterRegistry.gauge("getAll.total.price", totalPrice);
    }

    @Override
    public List<ProductDto> getAll() {
        return productService.getAll().stream()
            .map(productMapper::toProductDto)
            .peek(p-> totalPrice.addAndGet(p.getPrice().longValue()))
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

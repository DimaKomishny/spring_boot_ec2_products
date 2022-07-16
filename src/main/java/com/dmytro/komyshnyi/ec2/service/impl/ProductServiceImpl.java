package com.dmytro.komyshnyi.ec2.service.impl;

import com.dmytro.komyshnyi.ec2.entity.Product;
import com.dmytro.komyshnyi.ec2.repository.ProductRepository;
import com.dmytro.komyshnyi.ec2.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_NOT_FOUND = "product.not.found";
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public UUID save(Product product) {
        return productRepository.save(product).getId();
    }

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}

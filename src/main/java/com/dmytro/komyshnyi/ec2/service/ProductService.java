package com.dmytro.komyshnyi.ec2.service;

import com.dmytro.komyshnyi.ec2.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    UUID save(Product product);
    Product findById(UUID id);
    List<Product> getAll();
    void delete(UUID id);
}

package com.dmytro.komyshnyi.ec2.service;

import com.dmytro.komyshnyi.ec2.entity.Producer;
import com.dmytro.komyshnyi.ec2.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProducerService {

    List<Producer> getAll();
    Producer findById(UUID id);
    UUID save(Producer producer);
    void delete(UUID id);
    List<Product> findProductByProducerId(UUID producerId);
}

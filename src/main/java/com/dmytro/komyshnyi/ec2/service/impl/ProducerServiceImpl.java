package com.dmytro.komyshnyi.ec2.service.impl;

import com.dmytro.komyshnyi.ec2.entity.Producer;
import com.dmytro.komyshnyi.ec2.entity.Product;
import com.dmytro.komyshnyi.ec2.repository.ProducerRepository;
import com.dmytro.komyshnyi.ec2.repository.ProductRepository;
import com.dmytro.komyshnyi.ec2.service.ProducerService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class ProducerServiceImpl implements ProducerService {

    private static final String PRODUCER_NOT_FOUND = "producer.not.found";
    private ProducerRepository producerRepository;
    private ProductRepository productRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository,
                               ProductRepository productRepository) {
        this.producerRepository = producerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Producer> getAll() {
        return producerRepository.findAll(Sort.by(Sort.Order.desc("name")));
    }

    @Override
    public Producer findById(UUID id) {
        return producerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCER_NOT_FOUND));
    }

    @Override
    public UUID save(Producer producer) {
        producerRepository.save(producer);
        return producer.getId();
    }

    @Override
    public void delete(UUID id) {
        producerRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductByProducerId(UUID producerId) {
        return productRepository.findAllByProducerId(producerId);
    }
}

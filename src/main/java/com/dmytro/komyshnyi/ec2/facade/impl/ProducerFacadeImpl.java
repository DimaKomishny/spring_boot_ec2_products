package com.dmytro.komyshnyi.ec2.facade.impl;

import com.dmytro.komyshnyi.ec2.converter.ProducerMapper;
import com.dmytro.komyshnyi.ec2.converter.ProductMapper;
import com.dmytro.komyshnyi.ec2.dto.ProducerDto;
import com.dmytro.komyshnyi.ec2.dto.ProductDto;
import com.dmytro.komyshnyi.ec2.entity.Producer;
import com.dmytro.komyshnyi.ec2.facade.ProducerFacade;
import com.dmytro.komyshnyi.ec2.service.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProducerFacadeImpl implements ProducerFacade {

    private ProducerService producerService;
    private ProducerMapper producerMapper;
    private ProductMapper productMapper;

    public ProducerFacadeImpl(ProducerService producerService,
                              ProducerMapper producerMapper,
                              ProductMapper productMapper) {
        this.producerService = producerService;
        this.producerMapper = producerMapper;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProducerDto> getAll() {
        return producerService.getAll().stream()
                .map(producerMapper::toProducerDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProducerDto findById(UUID id) {
        Producer producer = producerService.findById(id);
        return producerMapper.toProducerDto(producer);
    }

    @Override
    public ResponseEntity<Map<String, UUID>> save(ProducerDto producerDto) {
        Producer producer = producerMapper.toProducer(producerDto);
        UUID id = producerService.save(producer);
        return new ResponseEntity<>(Map.of("id", id), HttpStatus.CREATED);
    }

    @Override
    public void delete(UUID id) {
        producerService.delete(id);
    }

    @Override
    public List<ProductDto> findProductByProducerId(UUID producerId) {
        return producerService.findProductByProducerId(producerId).stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }
}


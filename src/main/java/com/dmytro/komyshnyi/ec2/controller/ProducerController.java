package com.dmytro.komyshnyi.ec2.controller;

import com.dmytro.komyshnyi.ec2.dto.ProducerDto;
import com.dmytro.komyshnyi.ec2.dto.ProductDto;
import com.dmytro.komyshnyi.ec2.facade.ProducerFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ProducerController {

    private ProducerFacade producerFacade;

    public ProducerController(ProducerFacade producerFacade) {
        this.producerFacade = producerFacade;
    }

    @GetMapping("/producers")
    public List<ProducerDto> getAll() {
        return producerFacade.getAll();
    }

    @GetMapping("/producer/{id}")
    public ProducerDto get(@PathVariable UUID id) {
        return producerFacade.findById(id);
    }

    @GetMapping("/producer/{id}/products")
    public List<ProductDto> getProductByProducer(@PathVariable UUID id) {
        return producerFacade.findProductByProducerId(id);
    }

    @PostMapping("/producer")
    public ResponseEntity<Map<String, UUID>> save(@RequestBody ProducerDto producer) {
        return producerFacade.save(producer);
    }

    @DeleteMapping("/producer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        producerFacade.delete(id);
    }
}

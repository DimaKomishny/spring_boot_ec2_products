package com.dmytro.komyshnyi.ec2.facade;

import com.dmytro.komyshnyi.ec2.dto.ProducerDto;
import com.dmytro.komyshnyi.ec2.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProducerFacade {

    List<ProducerDto> getAll();
    ProducerDto findById(UUID id);
    ResponseEntity<Map<String, UUID>> save(ProducerDto producerDto);
    void delete(UUID id);
    List<ProductDto> findProductByProducerId(UUID producerId);
}

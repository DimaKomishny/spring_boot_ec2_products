package com.dmytro.komyshnyi.ec2.facade;

import com.dmytro.komyshnyi.ec2.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProductFacade {

    List<ProductDto> getAll();
    ProductDto findById(UUID id);
    ResponseEntity<Map<String, UUID>> save(ProductDto productDto);
    void delete(UUID id);
}

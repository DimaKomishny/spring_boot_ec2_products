package com.dmytro.komyshnyi.ec2.converter;

import com.dmytro.komyshnyi.ec2.dto.ProductDto;
import com.dmytro.komyshnyi.ec2.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private ProducerMapper producerMapper;

    public ProductMapper(ProducerMapper producerMapper) {
        this.producerMapper = producerMapper;
    }

    public ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .producer(producerMapper.toProducerDto(product.getProducer()))
                .build();
    }

    public Product toProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .producer(producerMapper.toProducer(productDto.getProducer()))
                .description(productDto.getDescription())
                .build();
    }
}

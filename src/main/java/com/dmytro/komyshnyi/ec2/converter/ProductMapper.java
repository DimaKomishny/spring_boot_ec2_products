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
                .price(product.getPrice())
                .producer(producerMapper.toProducerDto(product.getProducer()))
                .build();
    }

    public Product toProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .producer(producerMapper.toProducer(productDto.getProducer()))
                .build();
    }
}

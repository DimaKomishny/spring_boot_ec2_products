package com.dmytro.komyshnyi.ec2.converter;

import com.dmytro.komyshnyi.ec2.dto.ProducerDto;
import com.dmytro.komyshnyi.ec2.entity.Producer;
import org.springframework.stereotype.Component;

@Component
public class ProducerMapper {
    public ProducerDto toProducerDto(Producer producer) {
        return ProducerDto
                .builder()
                .id(producer.getId())
                .country(producer.getCountry())
                .name(producer.getName())
                .build();
    }

    public Producer toProducer(ProducerDto producerDto) {
        return Producer.builder()
                .id(producerDto.getId())
                .name(producerDto.getName())
                .country(producerDto.getCountry())
                .build();
    }
}

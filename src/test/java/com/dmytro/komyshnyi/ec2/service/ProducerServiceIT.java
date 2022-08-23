package com.dmytro.komyshnyi.ec2.service;

import com.dmytro.komyshnyi.ec2.config.AbstractIntegrationTest;
import com.dmytro.komyshnyi.ec2.entity.Producer;
import com.dmytro.komyshnyi.ec2.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProducerServiceIT extends AbstractIntegrationTest {

    @Autowired
    private ProducerService sut;
    @Autowired
    private EntityManager entityManager;


    @Test
    public void shouldSaveProducerAndProducts() {
        Producer expected = createProducer();

        UUID id = sut.save(expected);
        Producer actual = entityManager.find(Producer.class, id);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteProducer() {
        Producer persistProducer = createProducer();
        persistProducer.getProducts().forEach((p) -> p.setProducer(persistProducer));
        entityManager.persist(persistProducer);
        entityManager.flush();

        sut.delete(persistProducer.getId());
        boolean isPresent = entityManager.contains(persistProducer);

        assertFalse(isPresent);
    }

    private Producer createProducer() {
        return Producer.builder()
                .country(UUID.randomUUID().toString())
                .name(UUID.randomUUID().toString())
                .products(createProducts(2))
                .build();
    }

    private List<Product> createProducts(int number) {
        List<Product> products = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            products.add(createProduct());
        }
        return products;
    }

    private Product createProduct() {
        return Product.builder()
                .price(new BigDecimal(100))
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();
    }
}
package com.dmytro.komyshnyi.ec2.repository;

import com.dmytro.komyshnyi.ec2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.producer.id = :producerId")
    List<Product> findAllByProducerId(@Param("producerId") UUID producerId);
}

package com.dmytro.komyshnyi.ec2.repository;

import com.dmytro.komyshnyi.ec2.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProducerRepository extends JpaRepository<Producer, UUID> {
}

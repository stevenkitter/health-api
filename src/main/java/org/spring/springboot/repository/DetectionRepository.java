package org.spring.springboot.repository;

import org.spring.springboot.jpa.Detection;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DetectionRepository extends CrudRepository<Detection, Long> {
    Optional<Detection> findById(Long id);
}

package org.spring.springboot.repository;

import org.spring.springboot.jpa.Detection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DetectionRepository extends CrudRepository<Detection, Long> {
    Optional<Detection> findById(Long id);

    @Query("SELECT m FROM Detection m WHERE m.id>0 AND m.userId = :userId")
    List<Detection> findByUserId(@Param("userId") Long userId);
}

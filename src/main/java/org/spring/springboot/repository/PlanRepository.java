package org.spring.springboot.repository;

import org.spring.springboot.jpa.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlanRepository extends CrudRepository<Plan, Long> {
    Optional<Plan> findById(Long id);
}

package org.spring.springboot.repository;


import org.spring.springboot.jpa.MyPlan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MyPlanRepository extends CrudRepository<MyPlan, Long> {
    @Query("SELECT m FROM MyPlan m WHERE m.userId = :userId")
    Optional<MyPlan> findByUserId(@Param("userId") Long userId);
}

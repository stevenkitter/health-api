package org.spring.springboot.repository;

import org.spring.springboot.jpa.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {
    @Query("SELECT m FROM Card m WHERE m.id>0 AND m.userId = :userId")
    List<Card> findByUserId(Long userId);
}

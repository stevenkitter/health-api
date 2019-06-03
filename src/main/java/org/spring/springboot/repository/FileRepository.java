package org.spring.springboot.repository;

import org.spring.springboot.jpa.File;
import org.springframework.data.repository.CrudRepository;



public interface FileRepository extends CrudRepository<File, Long> {

}

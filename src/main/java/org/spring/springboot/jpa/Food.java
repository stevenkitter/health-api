package org.spring.springboot.jpa;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "foods")
public class Food extends Base {
    private Integer proteome;
    private Integer carbohydrate;
    private Integer fat;


}

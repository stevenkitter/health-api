package org.spring.springboot.jpa;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sports")
public class Sport extends Base {

    private String cycle;
    private String stage;
    private String target;

    @Column(name = "traning_time")
    private String traningTime;

    @Column(name = "plan_id")
    private Long planId;
}

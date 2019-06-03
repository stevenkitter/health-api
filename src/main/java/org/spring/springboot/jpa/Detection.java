package org.spring.springboot.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "detection")
public class Detection extends Base implements Serializable {
    private Integer height;
    private Integer weight;
    private Integer waist;
    private Integer bust;

    @Column(name = "blood")
    private float blood;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "user_id")
    private Long userId;
}

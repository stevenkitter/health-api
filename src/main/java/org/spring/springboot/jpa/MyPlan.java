package org.spring.springboot.jpa;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "my_plans")
public class MyPlan extends Base {

    @Column(name = "plan_id")
    private Long planId;

    @OneToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Plan plan;

    @Column(name = "user_id")
    private Long userId;


    @JsonInclude
    @Transient
    private Integer days;

    @JsonInclude
    @Transient
    private float percent;
}

package org.spring.springboot.jpa;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.models.Conclusion;

import javax.persistence.*;
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


    @Column(name = "user_id")
    private Long userId;



    @JsonInclude
    @Transient
    private Conclusion conclusion;

    @JsonInclude
    @Transient
    private String created;
}

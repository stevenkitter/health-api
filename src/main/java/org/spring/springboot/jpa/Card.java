package org.spring.springboot.jpa;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cards")
public class Card extends Base {
    private String content;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "user_id")
    private Long userId;


    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id", insertable = false, updatable = false)
    private File file;


    @JsonInclude
    @Transient
    private String created;
}

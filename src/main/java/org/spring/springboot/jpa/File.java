package org.spring.springboot.jpa;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "files")
public class File extends Base implements Serializable {
    private String fileName;

    @Column(name = "user_id")
    private Long userId;
}

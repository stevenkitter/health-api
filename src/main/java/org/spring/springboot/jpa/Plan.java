package org.spring.springboot.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "x_plan")
public class Plan extends Base implements Serializable {
    private String name;
    private Integer period;

    @OneToOne
    @JoinColumn(name = "fileId", referencedColumnName = "id", insertable = false, updatable = false)
    private File file;


    @Override
    public String toString() {
        return "Person [name=" + name + ", period=" + period + "]";
    }
    @Override
    public boolean equals(Object obj) {
        Plan p = (Plan) obj;
        return this.name.equals(p.name) && this.period == p.period;
    }

}

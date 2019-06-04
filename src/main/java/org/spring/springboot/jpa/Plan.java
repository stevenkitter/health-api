package org.spring.springboot.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "x_plan")
public class Plan extends Base implements Serializable {
    private String name;
    private Integer period;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "food_id")
    private Long foodId;

    @Column(name = "food_in")
    private Integer foodIn;
    private Integer consume;
    private String plan;
    private String description;
    private String secret;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id", insertable = false, updatable = false)
    private File file;

    @OneToOne
    @JoinColumn(name = "food_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Food food;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<Sport> sports;



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

package org.spring.springboot.models;


import lombok.Data;

import java.io.Serializable;

@Data
public class Conclusion implements Serializable {
    private String desc;
    private String waist;
    private String bust;
    private String blood;
}

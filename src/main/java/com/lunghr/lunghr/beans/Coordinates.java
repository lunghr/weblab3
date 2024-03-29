package com.lunghr.lunghr.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Named("coordinates")
@RequestScoped
public class Coordinates implements Serializable {
    private Double x = 0d;
    private Double y = 0d;
    private Double r=1d;
    @ToString.Exclude
    private final Double[] xValues = {-3d, -2d, -1d, 0d, 1d, 2d, 3d, 4d, 5d};
    @ToString.Exclude
    private final Double[] rValues = {1d, 2d, 3d, 4d, 5d};

}

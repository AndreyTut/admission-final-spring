package com.study.my.finalspring.admission.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Builder
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Diploma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Min(1)
    @Max(12)
    private Integer math;
    @Min(1)
    @Max(12)
    private Integer physics;
    @Min(1)
    @Max(12)
    private Integer history;
    @Min(1)
    @Max(12)
    private Integer literature;
    @Min(1)
    @Max(12)
    private Integer chemistry;
    @Min(1)
    @Max(12)
    private Integer biology;
    @OneToOne
    @ToString.Exclude
    private User user;
}

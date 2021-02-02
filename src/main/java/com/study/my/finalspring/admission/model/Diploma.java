package com.study.my.finalspring.admission.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private Integer math;
    @Min(1)
    @Max(12)
    @NotNull
    private Integer physics;
    @Min(1)
    @Max(12)
    @NotNull
    private Integer history;
    @Min(1)
    @Max(12)
    @NotNull
    private Integer literature;
    @Min(1)
    @Max(12)
    @NotNull
    private Integer chemistry;
    @Min(1)
    @Max(12)
    @NotNull
    private Integer biology;
    @OneToOne
    @ToString.Exclude
    private User user;
}

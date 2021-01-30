package com.study.my.finalspring.admission.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Diploma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer math;
    private Integer physics;
    private Integer history;
    private Integer literature;
    private Integer chemistry;
    private Integer biology;
    @OneToOne
    @ToString.Exclude
    private User user;
}

package com.cerberos.colorpool.entity.theme;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "THEME")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "THEME_ID", nullable = false)
    private Integer id;

    @Column(name = "R1", nullable = false)
    private Integer red1;
    @Column(name = "G1", nullable = false)
    private Integer green1;
    @Column(name = "B1", nullable = false)
    private Integer blue1;

    @Column(name = "R2", nullable = false)
    private Integer red2;
    @Column(name = "G2", nullable = false)
    private Integer green2;
    @Column(name = "B2", nullable = false)
    private Integer blue2;

    @Column(name = "R3", nullable = false)
    private Integer red3;
    @Column(name = "G3", nullable = false)
    private Integer green3;
    @Column(name = "B3", nullable = false)
    private Integer blue3;

    @Column(name = "R4", nullable = false)
    private Integer red4;
    @Column(name = "G4", nullable = false)
    private Integer green4;
    @Column(name = "B4", nullable = false)
    private Integer blue4;

    @Column(name = "R5", nullable = false)
    private Integer red5;
    @Column(name = "G5", nullable = false)
    private Integer green5;
    @Column(name = "B5", nullable = false)
    private Integer blue5;
}

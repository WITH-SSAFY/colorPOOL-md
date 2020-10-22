package com.cerberos.colorpool.entity.theme;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "RECOMMEND")
public class RecommendTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLOR_ID", nullable = false)
    private Integer id;

    @Column(name = "THEME_ID1", nullable = false)
    private Integer themeId1;

    @Column(name = "THEME_ID2", nullable = false)
    private Integer themeId2;

    @Column(name = "THEME_ID3", nullable = false)
    private Integer themeId3;

    @Column(name = "THEME_ID4", nullable = false)
    private Integer themeId4;

    @Column(name = "THEME_ID5", nullable = false)
    private Integer themeId5;

    @Column(name = "THEME_ID6", nullable = false)
    private Integer themeId6;

    @Column(name = "THEME_ID7", nullable = false)
    private Integer themeId7;

    @Column(name = "THEME_ID8", nullable = false)
    private Integer themeId8;

    @Column(name = "THEME_ID9", nullable = false)
    private Integer themeId9;

    @Column(name = "THEME_ID10", nullable = false)
    private Integer themeId10;
}

package com.cerberos.colorpool.model.theme;

import com.cerberos.colorpool.entity.theme.Theme;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThemeModel {
    private Integer red1;
    private Integer green1;
    private Integer blue1;
    private Integer red2;
    private Integer green2;
    private Integer blue2;
    private Integer red3;
    private Integer green3;
    private Integer blue3;
    private Integer red4;
    private Integer green4;
    private Integer blue4;
    private Integer red5;
    private Integer green5;
    private Integer blue5;

    @Builder
    public ThemeModel(Theme theme) {
        this.red1 = theme.getRed1();
        this.green1 = theme.getGreen1();
        this.blue1 = theme.getBlue1();
        this.red2 = theme.getRed2();
        this.green2 = theme.getGreen2();
        this.blue2 = theme.getBlue2();
        this.red3 = theme.getRed3();
        this.green3 = theme.getGreen3();
        this.blue3 = theme.getBlue3();
        this.red4 = theme.getRed4();
        this.green4 = theme.getGreen4();
        this.blue4 = theme.getBlue4();
        this.red5 = theme.getRed5();
        this.green5 = theme.getGreen5();
        this.blue5 = theme.getBlue5();
    }
}

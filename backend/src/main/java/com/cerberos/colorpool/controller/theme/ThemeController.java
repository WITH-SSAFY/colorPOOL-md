package com.cerberos.colorpool.controller.theme;

import com.cerberos.colorpool.entity.theme.Theme;
import com.cerberos.colorpool.service.theme.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/themes")
public class ThemeController {
    private final ThemeService themeService;

    @Operation(summary = "배색 조합 추천 받기")
    @GetMapping(value = "/{colorId}")
    public List<Theme> getThemes(@PathVariable Integer colorId) {
        return themeService.getThemes(colorId);
    }
}

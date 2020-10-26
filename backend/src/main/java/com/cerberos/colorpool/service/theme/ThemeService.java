package com.cerberos.colorpool.service.theme;

import com.cerberos.colorpool.entity.theme.RecommendTheme;
import com.cerberos.colorpool.entity.theme.Theme;
import com.cerberos.colorpool.repository.theme.RecommendJpaRepository;
import com.cerberos.colorpool.repository.theme.ThemeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final RecommendJpaRepository recommendJpaRepository;
    private final ThemeJpaRepository themeJpaRepository;

    public List<Theme> getThemes (Integer colorId) {
        RecommendTheme recommendTheme = recommendJpaRepository.findById(colorId).orElseThrow(IllegalArgumentException::new);

        List<Theme> themes = new ArrayList<>();

        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId1() + 1).orElseThrow(IllegalArgumentException::new));
        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId2() + 1).orElseThrow(IllegalArgumentException::new));
        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId3() + 1).orElseThrow(IllegalArgumentException::new));
        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId4() + 1).orElseThrow(IllegalArgumentException::new));
        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId5() + 1).orElseThrow(IllegalArgumentException::new));
        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId6() + 1).orElseThrow(IllegalArgumentException::new));
        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId7() + 1).orElseThrow(IllegalArgumentException::new));
        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId8() + 1).orElseThrow(IllegalArgumentException::new));
        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId9() + 1).orElseThrow(IllegalArgumentException::new));
        themes.add(themeJpaRepository.findById(recommendTheme.getThemeId10() + 1).orElseThrow(IllegalArgumentException::new));

        return themes;
    }
}

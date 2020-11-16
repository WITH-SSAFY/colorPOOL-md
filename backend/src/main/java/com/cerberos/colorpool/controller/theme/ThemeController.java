package com.cerberos.colorpool.controller.theme;

import com.cerberos.colorpool.entity.theme.Theme;
import com.cerberos.colorpool.model.response.ListResult;
import com.cerberos.colorpool.service.ResponseService;
import com.cerberos.colorpool.service.theme.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/themes")
public class ThemeController {
    private final ThemeService themeService;
    private final ResponseService responseService;

    @Operation(summary = "배색 조합 추천 받기", description = "색 선택 후 10개의 배색조합을 추천받는다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "배색 조합 검색 성공", content = @Content),
            @ApiResponse(responseCode = "500", description = "배색 조합 검색 실패", content = @Content)
    })
    @GetMapping(value = "/{colorId}")
    public ListResult<Theme> getThemes(@PathVariable Integer colorId) {
        return responseService.getListResult(themeService.getThemes(colorId));
    }
}

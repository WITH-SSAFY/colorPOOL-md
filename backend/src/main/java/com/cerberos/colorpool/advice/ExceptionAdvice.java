package com.cerberos.colorpool.advice;

import com.cerberos.colorpool.advice.exception.CThemeNotFoundException;
import com.cerberos.colorpool.repository.response.CommonResult;
import com.cerberos.colorpool.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e){
        return responseService.getFailResult(Integer.parseInt(getMessage("unknown.code")),getMessage("unknown.msg"));
    }

    @ExceptionHandler(CThemeNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult themeNotFoundException(HttpServletRequest request, CThemeNotFoundException e){
        return responseService.getFailResult(Integer.parseInt(getMessage("themeNotFoundException.code")),getMessage("themeNotFoundException.msg"));
    }


    // code정보에 해당하는 메시지를 조회
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}

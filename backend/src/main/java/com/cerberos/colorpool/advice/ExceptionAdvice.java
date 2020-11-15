package com.cerberos.colorpool.advice;

import com.cerberos.colorpool.advice.exception.CContentsNotFoundException;
import com.cerberos.colorpool.advice.exception.CImageNotUploadException;
import com.cerberos.colorpool.advice.exception.CPdfNotCreateException;
import com.cerberos.colorpool.advice.exception.CThemeNotFoundException;
import com.cerberos.colorpool.model.response.CommonResult;
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

    @ExceptionHandler(CPdfNotCreateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult pdfNotCreateException(HttpServletRequest request, CPdfNotCreateException e){
        return responseService.getFailResult(Integer.parseInt(getMessage("pdfNotCreateException.code")),getMessage("pdfNotCreateException.msg"));
    }

    @ExceptionHandler(CImageNotUploadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult imageNotUploadException(HttpServletRequest request, CImageNotUploadException e){
        return responseService.getFailResult(Integer.parseInt(getMessage("imageNotUploadException.code")),getMessage("imageNotUploadException.msg"));
    }

    @ExceptionHandler(CContentsNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult contentsNotFoundException(HttpServletRequest request, CContentsNotFoundException e){
        return responseService.getFailResult(Integer.parseInt(getMessage("contentsNotFoundException.code")),getMessage("contentsNotFoundException.msg"));
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

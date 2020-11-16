package com.cerberos.colorpool.advice.exception;

public class CThemeNotFoundException extends RuntimeException{
    public CThemeNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
    public CThemeNotFoundException(String msg) {
        super(msg);
    }
    public CThemeNotFoundException() {
        super();
    }
}

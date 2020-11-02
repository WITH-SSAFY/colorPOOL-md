package com.cerberos.colorpool.advice.exception;

public class CPdfNotCreateException extends RuntimeException {
    public CPdfNotCreateException(String msg, Throwable t){super(msg,t);}
    public CPdfNotCreateException(String msg){super(msg);}
    public CPdfNotCreateException(){super();}
}

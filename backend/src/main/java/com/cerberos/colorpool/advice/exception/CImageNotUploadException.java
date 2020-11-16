package com.cerberos.colorpool.advice.exception;

public class CImageNotUploadException extends RuntimeException{
    public CImageNotUploadException(String msg, Throwable t){super(msg,t);}
    public CImageNotUploadException(String msg){ super(msg);}
    public CImageNotUploadException(){ super();}
}

package br.com.servico.cliente.exceptions;

import org.springframework.http.HttpStatus;

public class UniqueException extends Exception {


    private static final long serialVersionUID = -8423302345212463478L;
    private String msg;
    private String titulo;
    private HttpStatus httpStatus;


    public UniqueException(String msg, String titulo, HttpStatus httpStatus) {
        this.msg = msg;
        this.titulo = titulo;
        this.httpStatus = httpStatus;
    }
    public UniqueException() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
package br.com.servico.cliente.exceptions;

public class ValidaCamposException  extends Exception {


    private static final long serialVersionUID = -7082212867266961646L;
    private String msg;

    public ValidaCamposException(String msg) {
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }




}
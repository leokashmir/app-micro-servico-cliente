package br.com.servico.cliente.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UniqueException.class)
    public ResponseEntity<?> handleUniqueException(UniqueException exc,
                                                   HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setData( LocalDate.now());
        errorDetail.setStatus(exc.getHttpStatus().value());
        errorDetail.setTitle(exc.getTitulo());
        errorDetail.setDetail(exc.getMessage());
        errorDetail.setDeveloperMessage(exc.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.OK);
    }


    @ExceptionHandler(ValidaCamposException.class)
    public ResponseEntity<?> handleValidaCamposException(ValidaCamposException vld,
                                                         HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setData( LocalDate.now());
        errorDetail.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorDetail.setTitle("Campo(s) Obrigatorio(s)");
        errorDetail.setDetail(vld.getMsg());
        errorDetail.setDeveloperMessage(vld.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> geralException(Exception exc,
                                            HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setData( LocalDate.now());
        errorDetail.setStatus(HttpStatus.OK.value());
        errorDetail.setTitle("Erro ===========");
        errorDetail.setDetail(exc.getMessage());
        errorDetail.setDeveloperMessage(exc.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.OK);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> erroInternoException(NullPointerException exc,
                                                  HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setData( LocalDate.now());
        errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetail.setDetail(exc.getMessage());


        return new ResponseEntity<>(errorDetail, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
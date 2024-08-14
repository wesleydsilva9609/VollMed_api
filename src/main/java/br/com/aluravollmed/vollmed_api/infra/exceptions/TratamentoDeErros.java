package br.com.aluravollmed.vollmed_api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratamentoErro404(){
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException methodArgumentNotValidException){
        var erros = methodArgumentNotValidException.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroDeValidacao::new).toList());
    }

    private record DadosErroDeValidacao(String campo,String mensagem){
        public DadosErroDeValidacao(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }
}

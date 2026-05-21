package com.example.gerenciador.hotel.shared.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(EnderecoJaExisteException.class)
    public ResponseEntity<Object> handleEnderecoJaExisteException(EnderecoJaExisteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(ExtratoJaExisteParaMesReferenteException.class)
    public ResponseEntity<Object> handleExtratoJaExisteParaMesReferenteException(ExtratoJaExisteParaMesReferenteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(FuncionarioJaExisteException.class)
    public ResponseEntity<Object> handleFuncionarioJaExisteException(FuncionarioJaExisteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(HospedeJaExisteException.class)
    public ResponseEntity<Object> handleHospedeJaExisteException(HospedeJaExisteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(QuartoJaExisteException.class)
    public ResponseEntity<Object> handleQuartoJaExisteException(QuartoJaExisteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(QuartoNaoEstaDisponivelParaDataException.class)
    public ResponseEntity<Object> handleQuartoNaoEstaDisponivelParaDataException(QuartoNaoEstaDisponivelParaDataException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TokenCreationException.class)
    public ResponseEntity<Object> handleTokenCreationException(TokenCreationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
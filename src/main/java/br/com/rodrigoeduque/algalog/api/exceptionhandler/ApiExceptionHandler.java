package br.com.rodrigoeduque.algalog.api.exceptionhandler;

import br.com.rodrigoeduque.algalog.domain.exception.NegocioException;
import br.com.rodrigoeduque.algalog.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex , HttpHeaders headers , HttpStatus status , WebRequest request) {

        List<Problema.Campo> campos = new ArrayList<>();

        for(ObjectError objectError : ex.getBindingResult().getAllErrors()){

            String nomeCampo = ((FieldError) objectError).getField();
            String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
            campos.add(new Problema.Campo(nomeCampo,mensagem));
        }

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos estão inválidos, realize o preenchimento corretamente e tente novamente");
        problema.setDataHora(OffsetDateTime.now());
        problema.setCampos(campos);

        return handleExceptionInternal(ex,problema,headers,status,request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex , problema , new HttpHeaders() , status , request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex , WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex , problema , new HttpHeaders() , status , request);
    }

}

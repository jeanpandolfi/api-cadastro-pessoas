package com.jeanpandolfi.cadastros.errors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Classe que intercepta as Exceptions e trata-as
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Customiza a Response para a exception MethodArgumentNotValidException
     * que é lançada quando a validação de um argumento anotado com {@code @Valid} falha. */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        ValidationErrorsOutput validationErrorsOutput =  buildValidationErrors(globalErrors, fieldErrors);
        validationErrorsOutput.setPath(request.getDescription(Boolean.FALSE));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorsOutput);
    }

    /**
     * Constroi um objeto {@link ValidationErrorsOutput} com base nos errors da request
     * @param globalErrors erros globais da request
     * @param fieldErrors campos de erros da request */
    private ValidationErrorsOutput buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {
        ValidationErrorsOutput validationErrors = new ValidationErrorsOutput();

        globalErrors.forEach( error -> validationErrors.addGlobalError(getErrorMessage(error)));

        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage, error.getRejectedValue());
        });
        return validationErrors;
    }

    /**
     * Busca a mensagem em {@link messages.properties} com base no ObjectError
     * @param error ObjectError
     * @return mensagem do messages.properties */
    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}

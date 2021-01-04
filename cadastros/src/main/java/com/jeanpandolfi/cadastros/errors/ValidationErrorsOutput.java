package com.jeanpandolfi.cadastros.errors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de retorno para reponses com Exceptions */
public class ValidationErrorsOutput {

    private String path;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<String> globalErrorsMessages = new ArrayList<>();
    private List<FielErrorOutput> fieldErrors = new ArrayList<>();


    public void addGlobalError(String message){
        this.globalErrorsMessages.add(message);
    }

    public void addFieldError(String field, String message, Object parameter){
        FielErrorOutput fielErrorOutputDTO = new FielErrorOutput(field, message, parameter);
        fieldErrors.add(fielErrorOutputDTO);
    }

    public List<FielErrorOutput> getFieldErrors() {
        return fieldErrors;
    }

    public List<String> getGlobalErrorsMessages() {
        return globalErrorsMessages;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

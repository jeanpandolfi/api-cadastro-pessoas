package com.jeanpandolfi.cadastros.errors;

/**
 * Classe que representa campos de erros da {@link ValidationErrorsOutput} */

public class FielErrorOutput {
    private String field;
    private String message;
    private Object rejectedValue;

    public FielErrorOutput(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public Object getRejectValue() {
        return rejectedValue;
    }
}

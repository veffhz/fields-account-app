package com.sixgrain.fields.app.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDetails {

    private ErrorDetails(ErrorType errorType, String description) {
        this.errorType = errorType;
        this.description = description;
    }

    enum ErrorType {
        INTERNAL_ERROR(2),
        ACCOUNT_NOT_EXIST(4),
        FIELD_NOT_EXIST(5);

        private int code;

        ErrorType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    @JsonIgnore
    private ErrorType errorType;
    private String description;

    @JsonProperty("Error")
    public String getError() {
        return description.toLowerCase().replace(" ", ".");
    }

    @JsonProperty("Code")
    public int getCode() {
        return errorType.getCode();
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    static ErrorDetails accountNotExist(String description) {
        return new ErrorDetails(ErrorType.ACCOUNT_NOT_EXIST, description);
    }

    static ErrorDetails fieldNotExist(String description) {
        return new ErrorDetails(ErrorType.FIELD_NOT_EXIST, description);
    }

    static ErrorDetails internalError(String description) {
        return new ErrorDetails(ErrorType.INTERNAL_ERROR, description);
    }

}

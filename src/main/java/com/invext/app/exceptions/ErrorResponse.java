package com.invext.app.exceptions;

import lombok.Builder;

@Builder
public class ErrorResponse {

    public String exceptionMessage;
    public String message;
    public String messageCode;

}

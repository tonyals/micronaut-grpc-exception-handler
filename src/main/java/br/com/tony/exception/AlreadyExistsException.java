package br.com.tony.exception;

import io.grpc.Status;

public class AlreadyExistsException extends BaseBusinessException {

    private final String errorMessage;
    private final Status.Code code;

    public AlreadyExistsException(String errorMessage, Status.Code code) {
        this.errorMessage = errorMessage;
        this.code = code;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }

    @Override
    public Status.Code statusCode() {
        return code;
    }
}

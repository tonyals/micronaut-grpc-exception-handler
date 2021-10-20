package br.com.tony.exceptions;

import br.com.tony.exception.BaseBusinessException;
import io.grpc.Status;

public class AlreadyExistsException extends BaseBusinessException {

    private static final String ALREADY_EXISTS = "Cliente já cadastrado no sistema";

    @Override
    public String errorMessage() {
        return ALREADY_EXISTS;
    }

    @Override
    public Status.Code statusCode() {
        return Status.Code.ALREADY_EXISTS;
    }
}

package br.com.content4devs.exception;

import io.grpc.Status;

public abstract class BaseBusinessException extends RuntimeException {
    public abstract String errorMessage();
    public abstract Status.Code statusCode();
}

package br.com.tony.controller;

import br.com.tony.ClientServiceGrpc;
import br.com.tony.ExceptionHandlerDemo;
import br.com.tony.exception.AlreadyExistsException;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class ClientController extends ClientServiceGrpc.ClientServiceImplBase {

    @Override
    public void create(ExceptionHandlerDemo.ClientRequest request, StreamObserver<ExceptionHandlerDemo.ClientResponse> responseObserver) {

        throw new AlreadyExistsException("Exception da lib", Status.Code.ALREADY_EXISTS);
    }
}

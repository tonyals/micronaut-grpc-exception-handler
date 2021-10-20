package br.com.tony.controller;

import br.com.tony.ClientServiceGrpc;
import br.com.tony.ExceptionHandlerDemo;
import br.com.tony.exceptions.AlreadyExistsException;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

import java.util.List;

@GrpcService
public class ClientController extends ClientServiceGrpc.ClientServiceImplBase {

    private static final List<String> clientsList = List.of("João", "Maria", "Nelson");

    @Override
    public void create(ExceptionHandlerDemo.ClientRequest request, StreamObserver<ExceptionHandlerDemo.ClientResponse> responseObserver) {
        boolean alreadyExists = clientsList.stream()
                .anyMatch((s) -> s.toUpperCase().contains(request.getName().toUpperCase()));

        if (alreadyExists) throw new AlreadyExistsException();

        ExceptionHandlerDemo.ClientResponse clientResponse = ExceptionHandlerDemo.ClientResponse.newBuilder()
                .setId(1L)
                .setName(request.getName())
                .setEmail(request.getEmail())
                .build();

        responseObserver.onNext(clientResponse);
        responseObserver.onCompleted();
    }
}

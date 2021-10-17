package br.com.tony.resources;

import br.com.tony.ClientServiceGrpc;
import br.com.tony.MicronautGrpcExceptionHandler;
import br.com.tony.exception.AlreadyExistsException;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class ClientResource extends ClientServiceGrpc.ClientServiceImplBase {

    @Override
    public void create(MicronautGrpcExceptionHandler.ClientRequest request, StreamObserver<MicronautGrpcExceptionHandler.ClientResponse> responseObserver) {
        throw new AlreadyExistsException("Erro ao criar cliente", Status.Code.ALREADY_EXISTS);
    }
}

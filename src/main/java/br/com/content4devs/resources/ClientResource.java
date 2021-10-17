package br.com.content4devs.resources;

import br.com.content4devs.ClientRequest;
import br.com.content4devs.ClientResponse;
import br.com.content4devs.ClientServiceGrpc;
import br.com.content4devs.exception.AlreadyExistsException;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class ClientResource extends ClientServiceGrpc.ClientServiceImplBase {

    @Override
    public void create(ClientRequest request, StreamObserver<ClientResponse> responseObserver) {
        throw new AlreadyExistsException("Erro ao criar cliente", Status.Code.ALREADY_EXISTS);
    }
}

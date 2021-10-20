package br.com.tony.listener;

import br.com.tony.exception.BaseBusinessException;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;

public class ExceptionHandlingServerCallListener<ReqT, RespT>
        extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {

    private final ServerCall<ReqT, RespT> serverCall;
    private final Metadata metadata;

    public ExceptionHandlingServerCallListener(ServerCall.Listener<ReqT> delegate, ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
        super(delegate);
        this.serverCall = serverCall;
        this.metadata = metadata;
    }

    @Override
    public void onHalfClose() {
        try {
            super.onHalfClose();
        } catch (BaseBusinessException e) {
            serverCall.close(e.statusCode().toStatus().withDescription(e.errorMessage()), metadata);
            throw e;
        }
    }

    @Override
    public void onCancel() {
        super.onCancel();
    }

    @Override
    public void onComplete() {
        super.onComplete();
    }

    @Override
    public void onReady() {
        try {
            super.onReady();
        } catch (BaseBusinessException e) {
            serverCall.close(e.statusCode().toStatus().withDescription(e.errorMessage()), metadata);
            throw e;
        }
    }
}

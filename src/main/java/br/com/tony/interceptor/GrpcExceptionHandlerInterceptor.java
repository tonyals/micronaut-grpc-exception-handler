package br.com.tony.interceptor;

import br.com.tony.exception.BaseBusinessException;
import io.grpc.*;

import javax.inject.Singleton;

@Singleton
public class GrpcExceptionHandlerInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        ServerCall.Listener<ReqT> listener = next.startCall(call, headers);
        return new ExceptionHandlingServerCallListener<>(listener, call, headers);
    }

    private static class ExceptionHandlingServerCallListener<ReqT, RespT>
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
            super.onReady();
        }
    }
}

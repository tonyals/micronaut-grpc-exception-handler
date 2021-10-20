package br.com.tony.interceptor;

import br.com.tony.listener.ExceptionHandlingServerCallListener;
import io.grpc.*;
import jakarta.inject.Singleton;

@Singleton
public class GrpcExceptionHandlerInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        ServerCall.Listener<ReqT> listener = next.startCall(call, headers);
        return new ExceptionHandlingServerCallListener<>(listener, call, headers);
    }
}

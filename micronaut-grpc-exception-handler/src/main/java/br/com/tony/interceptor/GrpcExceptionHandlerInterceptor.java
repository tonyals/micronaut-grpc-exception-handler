package br.com.tony.interceptor;

import br.com.tony.listener.ExceptionHandlingServerCallListener;
import io.grpc.*;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static br.com.tony.constants.LoggerConstants.REQUEST_RECEIVED;

@Singleton
public class GrpcExceptionHandlerInterceptor implements ServerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(GrpcExceptionHandlerInterceptor.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        logger.info(REQUEST_RECEIVED.getDescription());
        ServerCall.Listener<ReqT> listener = next.startCall(call, headers);
        return new ExceptionHandlingServerCallListener<>(listener, call, headers);
    }
}

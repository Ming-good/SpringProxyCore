package hello.proxy.config.v2_dynamicProxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceBasicHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace trace;

    public LogTraceBasicHandler(Object object, LogTrace logTrace) {
        this.target = object;
        this.trace = logTrace;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        Object result = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);
            result = method.invoke(target, args);
            trace.end(status);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}

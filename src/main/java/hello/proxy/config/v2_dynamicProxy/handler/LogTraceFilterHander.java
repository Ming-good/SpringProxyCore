package hello.proxy.config.v2_dynamicProxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.springframework.util.PatternMatchUtils;

public class LogTraceFilterHander implements InvocationHandler {

    private final Object target;
    private final LogTrace trace;
    private final String[] patterns;

    public LogTraceFilterHander(Object target, LogTrace trace, String[] patterns) {
        this.target = target;
        this.trace = trace;
        this.patterns = patterns;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();

        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }

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

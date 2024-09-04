package hello.proxy.config.proxy.concreate_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    private final OrderControllerV2 target;
    private final LogTrace trace;

    public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        String result = null;
        try {
            status = trace.begin("OrderServiceV2.request()");
            result = target.request(itemId);
            trace.end(status);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}

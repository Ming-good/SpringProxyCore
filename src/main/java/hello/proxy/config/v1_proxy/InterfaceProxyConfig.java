package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfraceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        return new OrderControllerInterfaceProxy(new OrderControllerV1Impl(orderService(logTrace)), logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        return new OrderServiceInterfaceProxy(new OrderServiceV1Impl(orderRepository(logTrace)), logTrace);
    }

    private OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        return new OrderRepositoryInterfraceProxy(new OrderRepositoryV1Impl(), logTrace);
    }
}

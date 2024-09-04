package hello.proxy.config.proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.proxy.concreate_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.proxy.concreate_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.proxy.concreate_proxy.OrderServiceConcreteProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {
    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace) {
        return new OrderControllerConcreteProxy(new OrderControllerV2(orderServices(logTrace)), logTrace);
    }

    @Bean
    public OrderServiceV2 orderServices(LogTrace logTrace) {
        return new OrderServiceConcreteProxy(new OrderServiceV2(orderRepositorys(logTrace)), logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositorys(LogTrace logTrace) {
        return new OrderRepositoryConcreteProxy(new OrderRepositoryV2(), logTrace);
    }
}

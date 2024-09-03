package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(realComponent);
        decoratorPatternClient.execute();
    }

    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        Component message = new MessageDecorator(realComponent);
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(message);
        decoratorPatternClient.execute();
    }

    @Test
    void decorator2() {
        Component realComponent = new RealComponent();
        Component timeDecorator = new TimeDecorator(realComponent);
        Component message = new MessageDecorator(timeDecorator);
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(message);
        decoratorPatternClient.execute();
    }
}

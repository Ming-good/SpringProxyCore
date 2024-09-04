package hello.proxy.pureproxy.concreateproxy;

import hello.proxy.pureproxy.concreateproxy.code.ConcreateClient;
import hello.proxy.pureproxy.concreateproxy.code.ConcreateLogic;
import hello.proxy.pureproxy.concreateproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy(){
        ConcreateClient client = new ConcreateClient(new ConcreateLogic());
        client.execute();
    }

    @Test
    void addProxy() {
        ConcreateLogic concreateLogic = new ConcreateLogic();
        TimeProxy timeProxy = new TimeProxy(concreateLogic);
        ConcreateClient client = new ConcreateClient(timeProxy);
        client.execute();
    }
}

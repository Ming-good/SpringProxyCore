package hello.proxy;


import hello.proxy.code.CacheProxy;
import hello.proxy.code.ProxyPatternClient;
import hello.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execut();
        client.execut();
        client.execut();
    }

    @Test
    void proxyTest() {
        ProxyPatternClient client = new ProxyPatternClient(new CacheProxy(new RealSubject()));
        client.execut();
        client.execut();
        client.execut();
    }
}

package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BImpl implements BInterface{

    static {
        System.out.println("하이");
    }
    @Override
    public String call() {
        log.info("B 호출");
        return "B";
    }
}

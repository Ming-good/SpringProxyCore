package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {


    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직1 시작
        log.info("START");
        String result1 = target.callA();
        log.info("resul={}", result1);

        log.info("START");
        String result2 = target.callB();
        log.info("resul={}", result2);

    }

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 클래스 정보
        Class<?> aClass = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        Method callA = aClass.getMethod("callA");
        Object result1 = callA.invoke(target);
        log.info("reault1 = {}", result1);

        Method callB = aClass.getMethod("callB");
        Object result2 = callB.invoke(target);
        log.info("reault1 = {}", result2);
    }

    @Test
    void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 클래스 정보
        Class<?> aClass = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        Method callA = aClass.getMethod("callA");
        dynamicCall(callA, target);

        Method callB = aClass.getMethod("callB");
        dynamicCall(callB, target);
    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("START");
        Object result = method.invoke(target);
        log.info("resul={}", result);
    }
    @Slf4j
    static class Hello {
        public String callA() {
            log.info("call A");
            return "A";
        }

        public String callB() {
            log.info("call B");
            return "B";
        }
    }

}

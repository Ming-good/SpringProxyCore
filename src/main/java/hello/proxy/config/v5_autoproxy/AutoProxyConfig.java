package hello.proxy.config.v5_autoproxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactry.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {
//    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        // 자동 프록시 생성기는 포인트 컷으로 프록시 적용 여부를 판단한다
        // 자동 프록시 생성기는 포인트 컷으로 어드바이스 적용 여부를 판단한다.
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

//    @Bean
    public Advisor advisor2(LogTrace logTrace) {
        /*
        *  " *                  : 모든 반환타입 "
        *  " hello.proxy.app..  : 해당 패키지와 그 하위 패키지
        *  " *(..)              : '*'은 모든 메서드 이름, '(..)' 파라미터 상관 없음
        * */
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..))");

        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

//    @Bean
    public Advisor advisor3(LogTrace logTrace) {
        /*
         *  " *                  : 모든 반환타입 "
         *  " hello.proxy.app..  : 해당 패키지와 그 하위 패키지
         *  " *(..)              : '*'은 모든 메서드 이름, '(..)' 파라미터 상관 없음
         * */
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))");

        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }
}

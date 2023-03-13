package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect  //AOP 쓰려면 추가해줘야함
@Component  //스프링빈에 등록
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
            //그냥 문법이 이렇게 생김. 매뉴얼 보고 그대로 써서 활용하기.. (hello.hellospring.service 이렇게 쓰면 service 하위의 파일에만 적용)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}

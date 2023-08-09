package by.ghoncharko.selectioninterview.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingServiceAspect {
    @Before("execution(* by.ghoncharko.selectioninterview.service.*.*(..))")
    public void logBeforeServiceMethod(JoinPoint joinPoint) {
        log.info("Before method {} called with args {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @After("execution(* by.ghoncharko.selectioninterview.service.*.*(..))")
    public void logAfterServiceMethod(JoinPoint joinPoint) {
        log.info("After method {} called with args {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }


}

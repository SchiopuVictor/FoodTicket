package foodticket.foodticket.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* foodticket.foodticket.repositpory.*.*(..))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object value = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        long duration = end - start;

        if (duration > 50) {
            logger.warn("Slow query detected: {}.{} executed in {} ms",
                    proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(),
                    proceedingJoinPoint.getSignature().getName(),
                    duration);
        }
        return value;
    }


}

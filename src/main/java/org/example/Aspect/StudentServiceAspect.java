package org.example.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class StudentServiceAspect {

    @Pointcut("within(org.example.Service..*)")
    public void serviceBeanPointcut() {
    }

//    @Before("@annotation(org.example.annotation.TimeDiffAnnotation)")
//    public void handleAddStudentBefore(JoinPoint jp) {
//        System.out.println("Inside Aspect before method "+jp.getThis());
//        System.out.println("Target: " + jp.getTarget());
//        System.out.println("Method: " + jp.getSignature().getName());
//        System.out.println("Arguments: " + Arrays.toString(jp.getArgs()));
//    }

    @Around("@annotation(org.example.annotation.TimeDiffAnnotation)")
    public Object handleAddStudentAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Inside Aspect before method " + joinPoint.getThis());
        System.out.println("Target: " + joinPoint.getTarget());
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));
        return joinPoint.proceed();

    }

    @Around("@annotation(org.example.annotation.TimeDiffAnnotation)")
    public Object handleCheckingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Inside Aspect before method " + joinPoint.getThis());
        System.out.println("Target: " + joinPoint.getTarget());
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));
        return joinPoint.proceed();

    }
}
package com.tangwh.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志组件
 */
@Component
@Aspect //表示一个切面
public class LogComponent {


    /**
     * 拦截规则  返回值任意  这个包下的任意类下的任意方法(参数也任意)
     */
    @Pointcut("execution(* com.tangwh.service.*.*(..))")
    public void pc1() {
    }

    /**
     * // Aop 5种通知 前置
     * //前置通知
     *
     * @param joinPoint
     */
    @Before(value = "pc1()")
    public void before(JoinPoint joinPoint) {
        // 拿到方法名
        String name = joinPoint.getSignature().getName();
        System.out.println("前置通知before" + name);
    }

    /**
     * 后置通知
     *
     * @param joinPoint
     */
    @After(value = "pc1()")
    public void after(JoinPoint joinPoint) {
        // 拿到名字
        String name = joinPoint.getSignature().getName();

        System.out.println("后置通知after" + name);
    }

    /**
     * 返回通知
     *
     * @param joinPoint
     */
    @AfterReturning(value = "pc1()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        // 拿到名字
        String name = joinPoint.getSignature().getName();

        System.out.println("返回通知afterReturning:" + name + "返回值:" + result);
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     */
    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        // 拿到名字
        String name = joinPoint.getSignature().getName();

        System.out.println("异常通知afterThrowing:" + name + "异常信息:" + e);
    }

    /**
     * 环绕通知 是前面四个的集成吧
     *
     * @param pid
     * @return
     * @throws Throwable
     */
    @Around(value = "pc1()")
    public Object around(ProceedingJoinPoint pid) throws Throwable {
        // 方法调用的返回值
        Object proceed = pid.proceed();
        return proceed;
    }
}

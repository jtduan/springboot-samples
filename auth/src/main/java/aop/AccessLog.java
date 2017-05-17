package aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jtduan on 2016/9/9.
 * 注意：save的时候级联对象不能为“游离态（会触发级联保存或者报错）”
 * Repo.getOne（类似于load()）与findOne(类似于get())区别
 * 在测试环境中，getPrincipal返回的不是User对象
 * Todo:增加注解函数支持，单独在一个日志中记录
 */

@Aspect
@Component
public class AccessLog {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * service..*.*(..))")
    public void method() {
    }


    @Around("method() && @annotation(debug)")
    public Object latencyService(ProceedingJoinPoint joinPoint, Debug debug) throws Throwable {
        logger.info(joinPoint.getSignature().getName() + "() execued:" + debug.value());
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        return result;
    }
}
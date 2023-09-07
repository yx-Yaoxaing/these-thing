package org.multi.source.multi.aespt;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.multi.source.multi.annotation.DB;
import org.multi.source.multi.DynamicDataSourceContextHolder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

//@Aspect
//@EnableAspectJAutoProxy
//@Component
public class DynamicDataSourceAesptConfig {

    /**
     * @within(xxx)"：如果某个类上标注了注解@A，那么该类中的所有方法就会被匹配为切点，并且该类的子类中没有重写的父类方法也会被匹配为切点
     * @target(xxx)"：如果某个类上标注了注解@A，那么该类中的所有方法就会被匹配为切点
     */

    // 切点
    @Pointcut("@annotation(org.multi.source.multi.annotation.DB)"
            + "|| @within(org.multi.source.multi.annotation.DB)")
    public void annotationMethod(){

    }

    @Around("annotationMethod()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        MethodSignature signature = (MethodSignature) point.getSignature();
        DB db = signature.getMethod().getAnnotation(DB.class);
        String name;
        if (db != null) {
             name = db.value();
        } else {
            DB dbAnnoatation = (DB) signature.getDeclaringType().getDeclaredAnnotation(DB.class);
            name = dbAnnoatation.value();
        }
        DynamicDataSourceContextHolder.setName(name);

        try {
           return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.destroy();
        }
    }

}

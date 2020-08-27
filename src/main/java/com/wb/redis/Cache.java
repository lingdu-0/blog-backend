package com.wb.redis;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 自定义注解,对于查询使用缓存的方法加入该注解
 */
//注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
@Retention(RetentionPolicy.RUNTIME)
//用于描述注解的使用范围 用于描述方法
@Target({ElementType.METHOD})
@Documented
public @interface Cache {
    /**
     * 设置方法体缓存标识
     *
     * @return
     */
    String value() default "";

    /**
     * key 过期日期 秒
     *
     * @return
     */
    int expireTime() default 60;

    /**
     * 时间单位，默认为秒
     *
     * @return
     */
    TimeUnit dateUnit() default TimeUnit.SECONDS;
}

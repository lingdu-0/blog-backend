package com.wb.redis;

import java.lang.annotation.*;

/**
 * 自定义注解删除缓存
 */
@Retention(RetentionPolicy.RUNTIME)
//用于描述注解的使用范围 用于描述方法
@Target({ElementType.METHOD})
@Documented
public @interface CacheEvict {

}

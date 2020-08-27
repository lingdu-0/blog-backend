package com.wb.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * redis缓存切面
 *
 * @author chenting
 */

@Aspect
@Component
public class GetCacheAOP {

    private static final Logger log = LoggerFactory.getLogger(CacheEvict.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Around(value = "@annotation(com.wb.redis.CacheEvict)")
    public Object handleCacheEvict(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取key前缀
        String className = joinPoint.getTarget().getClass().getName();
        Object result = joinPoint.proceed();
        //批量删除
        log.info(className + "删除缓存数据!");
        delDateToRedis(className + ".");
        return result;
    }

    /**
     * 将数据从redis中删除
     *
     * @param redisKey
     */
    public void delDateToRedis(String redisKey) {
        try {
            Set<String> set = redisTemplate.keys(redisKey + "*");
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String keyStr = (String) it.next();
                log.info("根据key:" + keyStr + "删除缓存信息");
                redisTemplate.delete(keyStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

}

package com.wb.test;

import com.wb.entity.User;
import com.wb.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserService userService;

    @Test
    public void lastOneArticle() {
        User user = userService.login("admin", "admin");
        System.out.println(user);
    }

    //    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test() {
        //GetCacheAOP getCacheAOP = new GetCacheAOP();
//        Object o = redisTemplate.opsForValue().get("com.wb.service.impl.LeaveWordServiceImpl.findAll.1");
//        System.out.println(o);
//        Set<Object> set = redisTemplate.keys("com.wb.service.impl.LeaveWordServiceImpl." + "*");
//        System.out.println(set);
//        redisTemplate.opsForValue().set("1", "a");

        //Iterator<Object> it = set.iterator();
        //redisTemplate.delete("com.wb.service.impl.LeaveWordServiceImpl.findAll.1");
        // getCacheAOP.delDateToRedis("com.wb.service.impl.LeaveWordServiceImpl.findAll.1");

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
    }
}

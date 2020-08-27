//package com.wb.redis;
//
//import com.wb.utils.SerializeUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import java.util.Iterator;
//import java.util.Set;
//
//public class RedisCache {
//    @Autowired
//    private JedisPool jedisPool;
//
//    public JedisPool getJedisPool() {
//        return jedisPool;
//    }
//
//    public void setJedisPool(JedisPool jedisPool) {
//        this.jedisPool = jedisPool;
//    }
//
//    /**
//     * 从redis缓存中查询，反序列化
//     *
//     * @param redisKey
//     * @return
//     */
//    public Object getDataFromRedis(String redisKey) {
//        //查询
//        Jedis jedis = jedisPool.getResource();
//        byte[] result = jedis.get(redisKey.getBytes());
//        //如果查询没有为空
//        if (null == result) {
//            return null;
//        }
//        System.out.println("成功从缓存中取到数据");
//        //查询到了，反序列化
//        return SerializeUtil.unserialize(result);
//    }
//
//    /**
//     * 将数据库中查询到的数据放入redis
//     *
//     * @param redisKey
//     * @param obj
//     */
//    public void setDataToRedis(String redisKey, Object obj) {
//        //序列化
//        byte[] bytes = SerializeUtil.serialize(obj);
//        //存入redis
//        Jedis jedis = jedisPool.getResource();
//        String success = jedis.set(redisKey.getBytes(), bytes);
//        if ("OK".equals(success)) {
//            System.out.println("数据成功保存到redis...");
//        }
//    }
//
//    /**
//     * 将数据从redis中删除
//     *
//     * @param redisKey
//     */
//    public void delDateToRedis(String redisKey) {
//        Jedis jedis = jedisPool.getResource();
//        try {
//            Set<String> set = jedis.keys(redisKey + "*");
//            Iterator<String> it = set.iterator();
//            while (it.hasNext()) {
//                String keyStr = it.next();
//                System.out.println("根据key" + keyStr + "删除缓存信息");
//                System.out.println(keyStr);
//                jedis.del(keyStr);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (jedis != null)
//                jedis.close();
//        }
//    }
//}
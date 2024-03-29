package com.shiliu.game.utils;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** 
* @author popl 
* @version 1.0
* @createDate 2016年6月14日 下午1:20:42 
* @description
*/
public class RedisUtil {
	static Logger logger = Logger.getLogger(RedisUtil.class);
	//Redis服务器IP
    private static String ADDR = PropertyUtil.getProperty("jedis_url");
    
    //Redis的端口号
    private static int PORT = PropertyUtil.getIntProperty("jedis_port");
    
    //访问密码
    private static String AUTH = PropertyUtil.getProperty("jedis_auth");
    
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 100;
    
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 20;
    
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;
    
    private static int TIMEOUT = 10000;
    
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    
    private static JedisPool jedisPool = null;
    
    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT,AUTH);
        } catch (Exception e) {
        	logger.error("初始化redis连接池异常",e);
        }
    }
    
    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
	
	public static void setObject(String key ,Object object){
		String value = JsonUtil.toJSONString(object);
		jedisPool.getResource().set(key, value);
	}
	
	public static <T> T getObject(String key,Class<T> clazz){
		String value = jedisPool.getResource().get(key);
		if(value == null)
			return null;
		return JsonUtil.parseObject(value, clazz);
	}
	
}

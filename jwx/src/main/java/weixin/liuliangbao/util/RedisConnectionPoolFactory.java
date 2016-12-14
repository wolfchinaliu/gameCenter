package weixin.liuliangbao.util;

import java.util.HashMap;

import org.jeecgframework.core.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;


public class RedisConnectionPoolFactory {

    private static transient final Logger LOGGER = LoggerFactory
            .getLogger(RedisConnectionPoolFactory.class);

    
    private static HashMap<String, JedisPool> poolMap = new HashMap<String, JedisPool>();
    
    private static String DEFULT_JEDIS_POOL_NAME = "DEFAULT_JEDIS_POOL";
    
    private static int timeout = 6000;

    static {
    	String hosts = ResourceUtil.getRedisHosts();
    	String[] hostAndPort = hosts.trim().split(":");
    	String ip = hostAndPort[0];
    	String port = hostAndPort[1];
    	
    	JedisPoolConfig jConf = new JedisPoolConfig();
        jConf.setMaxIdle(-1);
        JedisPool pool = new JedisPool(jConf, ip, Integer.parseInt(port),
        		timeout);
        poolMap.put(DEFULT_JEDIS_POOL_NAME, pool);
    }
    
    public static Jedis getResource(){
        Jedis jedis = null;
        JedisPool pool = poolMap.get(DEFULT_JEDIS_POOL_NAME);
        if (pool != null) {
            int tryCount = 0;
            int maxTryCount = 3;
            while (tryCount < maxTryCount) {
                try {
                    tryCount++;
                    jedis = pool.getResource();
                    break;
                } catch (JedisConnectionException e) {
                    pool.returnBrokenResource(jedis);
                    if (tryCount == maxTryCount) {
                        LOGGER.warn(e.getMessage(), e);
                        throw e;
                    } else {
                        continue;
                    }
                }
            }
        }

        return jedis;
    }

    /**
     * 回收链接对象
     *
     * @param dataSourceName
     * @param conn
     */
    public static void returnResource(Jedis conn) {
        if (conn == null) {
            return;
        }

        JedisPool pool = poolMap.get(DEFULT_JEDIS_POOL_NAME);
        if (pool != null) {
            pool.returnResource(conn);
        }
    }

    public static void returnBrokenResource(Jedis conn) {
        if (conn == null) {
            return;
        }

        JedisPool pool = poolMap.get(DEFULT_JEDIS_POOL_NAME);
        if (pool != null) {
            pool.returnBrokenResource(conn);
        }
    }
    
    public static void main(String[] args) {
    	Jedis jedis = null;
    	try {
    		jedis = RedisConnectionPoolFactory.getResource();
        	jedis.set("yanqiao_test", "yanqiao_test1");
        	LOGGER.info(jedis.get("yanqiao_test"));
    	} catch (Exception ex) {
    		RedisConnectionPoolFactory.returnBrokenResource(jedis);
    		jedis = null;
    	} finally {
    		RedisConnectionPoolFactory.returnResource(jedis);
    	}
    }
}

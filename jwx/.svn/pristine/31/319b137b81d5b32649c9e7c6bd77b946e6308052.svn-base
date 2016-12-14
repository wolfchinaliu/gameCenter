package weixin.guanjia.core.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import weixin.liuliangbao.util.RedisConnectionPoolFactory;

/**
 * Created by GuoLiang on 2016/5/11 18:35.
 */
public class RedisUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    public static byte[] getRedis(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = RedisConnectionPoolFactory.getResource();
            return jedis.get(key);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            RedisConnectionPoolFactory.returnBrokenResource(jedis);
            jedis = null;
            return null;
        } finally {
            RedisConnectionPoolFactory.returnResource(jedis);
        }
    }

    public static String getRedis(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisConnectionPoolFactory.getResource();
            return jedis.get(key);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            RedisConnectionPoolFactory.returnBrokenResource(jedis);
            jedis = null;
            return null;
        } finally {
            RedisConnectionPoolFactory.returnResource(jedis);
        }
    }

    public static String getRedis(String key, String field, Integer expireTime) {
        Jedis jedis = null;
        try {
            jedis = RedisConnectionPoolFactory.getResource();
            if (null != expireTime) {
                return jedis.hget(key, field);
            } else {
                return jedis.hget(key, field);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            RedisConnectionPoolFactory.returnBrokenResource(jedis);
            jedis = null;
            return null;
        } finally {
            RedisConnectionPoolFactory.returnResource(jedis);
        }
    }

    public static void setRedis(String key, String field, String value, Integer expireTime) {
        Jedis jedis = null;
        try {
            jedis = RedisConnectionPoolFactory.getResource();
            jedis.hset(key, field, value);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            RedisConnectionPoolFactory.returnBrokenResource(jedis);
            jedis = null;
        } finally {
            RedisConnectionPoolFactory.returnResource(jedis);
        }
    }



    public static void setRedis(byte[] key, int expreTime, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = RedisConnectionPoolFactory.getResource();
            jedis.setex(key, expreTime, value);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            RedisConnectionPoolFactory.returnBrokenResource(jedis);
            jedis = null;
        } finally {
            RedisConnectionPoolFactory.returnResource(jedis);
        }
    }

    public static void setRedis(String key, int expreTime, String value) {
        Jedis jedis = null;
        try {
            jedis = RedisConnectionPoolFactory.getResource();
            jedis.setex(key, expreTime, value);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            RedisConnectionPoolFactory.returnBrokenResource(jedis);
            jedis = null;
        } finally {
            RedisConnectionPoolFactory.returnResource(jedis);
        }
    }

    public static void setRedis(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = RedisConnectionPoolFactory.getResource();
            jedis.set(key, value);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            RedisConnectionPoolFactory.returnBrokenResource(jedis);
            jedis = null;
        } finally {
            RedisConnectionPoolFactory.returnResource(jedis);
        }
    }

    public static void del(String key) {
        if (StringUtils.isBlank(key)) return;
        Jedis jedis = null;
        try {
            jedis = RedisConnectionPoolFactory.getResource();
            jedis.del(key);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            RedisConnectionPoolFactory.returnBrokenResource(jedis);
            jedis = null;
        } finally {
            RedisConnectionPoolFactory.returnResource(jedis);
        }
    }
}

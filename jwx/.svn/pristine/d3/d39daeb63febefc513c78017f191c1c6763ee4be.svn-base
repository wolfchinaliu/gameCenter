package weixin.guanjia.core.util;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.openplatform.controller.WeixinOpenPlatformController.*;
import weixin.liuliangbao.util.SerializeUtil;

public class PageAuthRedisCache {
    private static transient final Logger LOGGER = LoggerFactory.getLogger(PageAuthRedisCache.class);
	
	private static final String PAGE_AUTH_PROPERTY_PREFIX = "page_auth_property_";
	private static final String PAGE_AUTH_ACCOUNT_ID_PREFIX = "page_auth_account_id_";
	private static final String PAGE_AUTH_HANDLER_PREFIX = "page_auth_handler_";
	private static final String PAGE_AUTH_OPEN_ID_PREFIX = "page_auth_open_id_";
	
	// 30分钟失效 单位:秒
	private static final Integer KEY_EXPIRE_TIME = 1800;
    public static final Integer KEY_EXPIRE_TIME_TWO_WEEK = 2 * 7 * 24 * 3600;
    public static final Integer KEY_EXPIRE_TIME_ONE_WEEK = 1 * 7 * 24 * 3600;
    private static  final String  PAGE_AUTH_CALLBACK_PREFIX = "page_auth_callback_";

    // Jedis cluster implementation
	/* public static void setPropertyCache(String key, PageAuthPropertyCache cache) {
		try {
			JedisCluster cluster = JedisClusterFactory.getDefaultJedisCluster();
			cluster.setex(PAGE_AUTH_PROPERTY_PREFIX + key, KEY_EXPIRE_TIME, String.valueOf(SerializeUtil.serialize(cache)));
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

	}
	
	public static void setAccountIdCache(String key, AccountIdCache cache) {
		try {
			JedisCluster cluster = JedisClusterFactory.getDefaultJedisCluster();
			cluster.setex(PAGE_AUTH_ACCOUNT_ID_PREFIX + key, KEY_EXPIRE_TIME, String.valueOf(SerializeUtil.serialize(cache)));
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	public static void setPageAuthHandlerCache(String key, PageAuthHandlerCache cache) {
		try {
			JedisCluster cluster = JedisClusterFactory.getDefaultJedisCluster();
			cluster.setex(PAGE_AUTH_HANDLER_PREFIX + key, KEY_EXPIRE_TIME, String.valueOf(SerializeUtil.serialize(cache)));
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

	}
	
	public static void setOpenIdCache(String key, OpenIdCache cache) {
		try {
			JedisCluster cluster = JedisClusterFactory.getDefaultJedisCluster();
			cluster.setex(PAGE_AUTH_OPEN_ID_PREFIX + key, KEY_EXPIRE_TIME, String.valueOf(SerializeUtil.serialize(cache)));
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

	}
	
	public static PageAuthPropertyCache getPropertyCache(String key) {
		try {
			JedisCluster cluster = JedisClusterFactory.getDefaultJedisCluster();
			byte[] value = cluster.get((PAGE_AUTH_PROPERTY_PREFIX + key).getBytes("UTF8"));
			return value == null ? null : (PageAuthPropertyCache)SerializeUtil.unserialize(value);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	public static AccountIdCache getAccountIdCache(String key) {
		try {
			JedisCluster cluster = JedisClusterFactory.getDefaultJedisCluster();
			byte[] value = cluster.get((PAGE_AUTH_ACCOUNT_ID_PREFIX + key).getBytes("UTF8"));
			return value == null ? null : (AccountIdCache)SerializeUtil.unserialize(value);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	public static PageAuthHandlerCache getPageAuthHandlerCache(String key) {
		try {
			JedisCluster cluster = JedisClusterFactory.getDefaultJedisCluster();
			byte[] value = cluster.get((PAGE_AUTH_HANDLER_PREFIX + key).getBytes("UTF8"));
			return value == null ? null : (PageAuthHandlerCache)SerializeUtil.unserialize(value);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	public static OpenIdCache getOpenIdCache(String key) {
		try {
			JedisCluster cluster = JedisClusterFactory.getDefaultJedisCluster();
			byte[] value = cluster.get((PAGE_AUTH_OPEN_ID_PREFIX + key).getBytes("UTF8"));
			return value == null ? null : (OpenIdCache)SerializeUtil.unserialize(value);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return null;
		}
	} */
	
	public static void setPropertyCache(String key, PageAuthPropertyCache cache) {
        RedisUtil.setRedis((PAGE_AUTH_PROPERTY_PREFIX + key).getBytes(), KEY_EXPIRE_TIME, SerializeUtil.serialize(cache));
	}
	
	public static void setAccountIdCache(String key, AccountIdCache cache) {
		RedisUtil.setRedis((PAGE_AUTH_ACCOUNT_ID_PREFIX + key).getBytes(), KEY_EXPIRE_TIME, SerializeUtil.serialize(cache));
	}
	
	public static void setPageAuthHandlerCache(String key, PageAuthHandlerCache cache) {
        RedisUtil.setRedis(PAGE_AUTH_HANDLER_PREFIX + key, KEY_EXPIRE_TIME, cache.getHandler().getClass().getSimpleName());
	}
	
	public static void setOpenIdCache(String key, OpenIdCache cache) {
        RedisUtil.setRedis((PAGE_AUTH_OPEN_ID_PREFIX + key).getBytes(), KEY_EXPIRE_TIME, SerializeUtil.serialize(cache));
	}
	
	public static PageAuthPropertyCache getPropertyCache(String key) {
        byte[] value = RedisUtil.getRedis((PAGE_AUTH_PROPERTY_PREFIX + key).getBytes());
        return value == null ? null : (PageAuthPropertyCache)SerializeUtil.unserialize(value);
	}

	public static AccountIdCache getAccountIdCache(String key) {
        byte[] value = RedisUtil.getRedis((PAGE_AUTH_ACCOUNT_ID_PREFIX + key).getBytes());
        return value == null ? null : (AccountIdCache)SerializeUtil.unserialize(value);
	}

	public static PageAuthHandlerCache getPageAuthHandlerCache(String key) {
        String value = RedisUtil.getRedis(PAGE_AUTH_HANDLER_PREFIX + key);
        PageAuthHandlerCache cache = new PageAuthHandlerCache();
        cache.setHandler((PageAuthHandler)ApplicationContextUtil.getContext().getBean(getCamelName(value)));
        return cache;
	}

	public static PageAuthCallback getPageAuthCallbackCache(String key) {
        byte[] value = RedisUtil.getRedis((PAGE_AUTH_CALLBACK_PREFIX + key).getBytes());
        return value == null ? null : (PageAuthCallback) SerializeUtil.unserialize(value);
	}

	public static OpenIdCache getOpenIdCache(String key) {
        byte[] value = RedisUtil.getRedis((PAGE_AUTH_OPEN_ID_PREFIX + key).getBytes());
        return value == null ? null : (OpenIdCache)SerializeUtil.unserialize(value);
	}

	private static String getCamelName(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        if (name.length() == 1) return name.toLowerCase();
        return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

    public static void setPageAuthCallbackCache(String key, PageAuthCallback pageAuthCallback) {
        RedisUtil.setRedis((PAGE_AUTH_CALLBACK_PREFIX + key).getBytes(), KEY_EXPIRE_TIME, SerializeUtil.serialize(pageAuthCallback));
    }

}

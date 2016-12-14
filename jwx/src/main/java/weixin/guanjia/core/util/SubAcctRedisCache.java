package weixin.guanjia.core.util;

import java.util.List;

import weixin.guanjia.openplatform.controller.WeixinOpenPlatformController.PageAuthPropertyCache;
import weixin.liuliangbao.util.SerializeUtil;
import weixin.tenant.entity.WeixinAcctEntity;

/**
 * @author parallel_line
 * @version 2016年9月26日 下午7:47:12
 */

public class SubAcctRedisCache {
    private static final String ALL_SUBACCT_PREFIX = "all_subacct_";
    private static final String ALL_NOTC_SUBACCT_PREFIX = "all_notc_subacct_";

    private static final Integer KEY_EXPIRE_TIME = 60 * 30;
    public static final Integer KEY_EXPIRE_TIME_TWO_WEEK = 2 * 7 * 24 * 3600;
    public static final Integer KEY_EXPIRE_TIME_ONE_WEEK = 1 * 7 * 24 * 3600;

    public static void setAllSubAcct(String merchantId, List<WeixinAcctEntity> subAcct) {
        RedisUtil.setRedis((ALL_SUBACCT_PREFIX + merchantId).getBytes(), KEY_EXPIRE_TIME, SerializeUtil.serialize(subAcct));
    }

    public static void setNotCAllSubAcct(String merchantId, List<WeixinAcctEntity> subAcct) {
        RedisUtil.setRedis((ALL_NOTC_SUBACCT_PREFIX + merchantId).getBytes(), KEY_EXPIRE_TIME, SerializeUtil.serialize(subAcct));
    }

    public static List<WeixinAcctEntity> getAllSubAcct(String merchantId) {
        byte[] value = RedisUtil.getRedis((ALL_SUBACCT_PREFIX + merchantId).getBytes());
        return value == null ? null : (List<WeixinAcctEntity>) SerializeUtil.unserialize(value);
    }

    public static List<WeixinAcctEntity> getNotCAllSubAcct(String merchantId) {
        byte[] value = RedisUtil.getRedis((ALL_NOTC_SUBACCT_PREFIX + merchantId).getBytes());
        return value == null ? null : (List<WeixinAcctEntity>) SerializeUtil.unserialize(value);
    }
}
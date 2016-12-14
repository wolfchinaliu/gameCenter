package weixin.liuliangbao.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.jeecgframework.core.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterFactory {
    private static transient final Logger LOGGER = LoggerFactory
            .getLogger(JedisClusterFactory.class);

//    private static final ResourceBundle properties = java.util.ResourceBundle.getBundle("redis");
    private static HashMap<String, JedisCluster> jedisClusterPool = new HashMap<String, JedisCluster>();

    private static int timeout = 6000;
    private static int maxRedirections = 5000;
    private static int maxTotal = 5000;
    private static int maxIdle = 500;

    private static String DEFULT_CLUSTER_NAME = "DEFAULT_CLUSTER";

    static {
        try {
            //InputStream inputStream = JedisClusterFactory.class.getResourceAsStream("redis.properties");
            //properties.load(inputStream);
            

            if (StringUtils.isNotBlank(ResourceUtil.getRedisTimeout())) {
                timeout = Integer.parseInt(ResourceUtil.getRedisTimeout());
            }

            if (StringUtils.isNotBlank(ResourceUtil.getRedisMaxRedirections())) {
                maxRedirections = Integer.parseInt(ResourceUtil.getRedisMaxRedirections());
            }
            
            if (StringUtils.isNotBlank(ResourceUtil.getRedisMaxTotal())) {
                maxTotal = Integer.parseInt(ResourceUtil.getRedisMaxTotal());
            }

            if (StringUtils.isNotBlank(ResourceUtil.getRedisMaxIdle())) {
                maxIdle = Integer.parseInt(ResourceUtil.getRedisMaxIdle());
            }

            for (Object prop : ResourceUtil.getRedisConfigs().keySet()) {
                String key = prop.toString();
                if (key.startsWith("redis.hosts")) {
                    String[] tokens = key.split("\\.");
                    String clusterName;
                    if (tokens.length == 3) {
                        clusterName = tokens[2];
                    } else {
                        LOGGER.warn(String.format("没有指定cluster name 指定为默认。%s = %s", key,
                                ResourceUtil.getRedisConfigs().getString(key)));
                        clusterName = DEFULT_CLUSTER_NAME;
                    }

                    String hosts = ResourceUtil.getRedisConfigs().getString(key);
                    if (StringUtils.isBlank(hosts)) {
                        String errorMsg = String.format(
                                "Redis配置文件异常，Redis主机列表格式不正确：%s = %s，Redis主机列表不能为空", key,
                                ResourceUtil.getRedisConfigs().getString(key));
                        LOGGER.error(errorMsg);

                        throw new RuntimeException(errorMsg);
                    }

                    Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
                    String[] hostList = hosts.trim().split(";");
                    for (String h : hostList) {
                        String[] pair = h.split(":");
                        String hostname = pair[0];
                        Integer port = Integer.parseInt(pair[1]);

                        jedisClusterNodes.add(new HostAndPort(hostname, port));
                    }

                    GenericObjectPoolConfig config = new GenericObjectPoolConfig();
                    config.setMaxTotal(maxTotal);
                    config.setMaxIdle(maxIdle);

                    jedisClusterPool.put(clusterName, new JedisCluster(jedisClusterNodes, timeout, maxRedirections, config));
                }
            }
        } catch (Exception e) {
            String errorMsg = String.format("Redis集群初始化失败，errorMsg = %s", e.getMessage());
            LOGGER.error(errorMsg, e);
        }
    }

    public static JedisCluster getJedisCluster(String clusterName) {
        JedisCluster jedisCluster = jedisClusterPool.get(clusterName);
        if (jedisCluster == null) {
            String errorMsg = String.format("获取名称为%s的Redis集群失败，请检查Redis配置并验证Redis服务是否正常启动。",
                    clusterName);
            LOGGER.error(errorMsg);

            // TODO 抛异常or直接返回
        }

        return jedisCluster;
    }

    public static JedisCluster getDefaultJedisCluster()  {
        return getJedisCluster(DEFULT_CLUSTER_NAME);
    }
    
    public static void main(String[] args) {
    	JedisCluster cluster = JedisClusterFactory.getDefaultJedisCluster();
    	cluster.set("yanqiao_test", "yanqiao_test");
    	LOGGER.info(cluster.get("yanqiao_test"));
    }
}

package org.jeecgframework.web.system.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.web.system.pojo.base.Client;

import weixin.liuliangbao.util.JedisClusterFactory;
import weixin.liuliangbao.util.RedisConnectionPoolFactory;

/**
 * 对在线用户的管理
 * @author JueYue
 * @date 2013-9-28
 * @version 1.0
 */
public class ClientManager {
	
	private static ClientManager instance = new ClientManager();
	
	private ClientManager(){
		
	}
	
	public static ClientManager getInstance(){
		return instance;
	}
	
	private Map<String,Client> map = new HashMap<String, Client>();
	
	/**
	 * 
	 * @param sessionId
	 * @param client
	 */
	public void addClinet(String sessionId,Client client){
		ContextHolderUtils.getSession().setAttribute(sessionId + "_client", client);
	}
	/**
	 * sessionId
	 */
	public void removeClinet(String sessionId){
		ContextHolderUtils.getSession().removeAttribute(sessionId + "_client");
	}
	/**
	 * 
	 * @param sessionId
	 * @return
	 */
	public Client getClient(String sessionId){
		return (Client)ContextHolderUtils.getSession().getAttribute(sessionId + "_client");
	}
	/**
	 *
	 * @return
	 */
	public Client getClient(){
		return getClient(ContextHolderUtils.getSession().getId());
	}
	/**
	 * 
	 * @return
	 */
	public Collection<Client> getAllClient(){
		return map.values();
	}

}

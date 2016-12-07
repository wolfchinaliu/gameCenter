package com.shiliu.game.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.shiliu.game.domain.bean.FlowResult;
import com.shiliu.game.utils.EncryptUtil;
import com.shiliu.game.utils.JsonUtil;
import com.shiliu.game.utils.PropertyUtil;

/**
 * @Auth popl_lu
 * @Date 2016年7月21日 上午11:33:35
 * @authEmail popl_lu@sian.cn
 * @CalssName com.shiliu.game.common.FlowOperator
 * @dec 
 */
public class FlowOperator {

	static Logger logger = Logger.getLogger(FlowOperator.class);
	public static FlowResult hlenFlowGZ(String gameId ,String flowValue,String openId){
			return hlenFlow(gameId, flowValue, openId,PropertyUtil.getProperty("gz_sercrt_key"));
	}
	
	public static FlowResult hlenFlowJHDX(String gameId ,String flowValue,String openId){
		return hlenFlow(gameId, flowValue, openId,PropertyUtil.getProperty("jhdx_sercrt_key"));
}
	public static FlowResult hlenFlow(String gameId ,String flowValue,String openId,String sercrt_key){
		FlowResult result = null;
		try {
			//数据封装
			String jsonString = "{\"flowValue\":\""+flowValue+"\",\"openId\":\""+openId+"\"}";
			logger.info(jsonString);
			if(sercrt_key == null) sercrt_key = PropertyUtil.getProperty("sercrt_key");
			String data = EncryptUtil.DESencryptToString(jsonString, sercrt_key);
			JSONObject reqJsonObject = new JSONObject();
			reqJsonObject.put("gameId", gameId);
			reqJsonObject.put("data", data);
			logger.info(" 获取key 请求信息："+ reqJsonObject);
			//开始请求
			URL aURL = new URL(PropertyUtil.getProperty("hend_flow_url"));
			HttpURLConnection connection = (HttpURLConnection) aURL.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-type","application/json;charset=UTF-8");
			connection.setDoOutput(true);
	        connection.setDoInput(true);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));   
			writer.write(reqJsonObject.toString());   
			writer.flush();   
			writer.close();   
			connection.connect();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String resString = new String("");
			String temp = null;
			while ((temp = buffer.readLine()) != null) {
				resString += temp;
			}
			logger.info("获取key 返回信息 ：" +resString);
			result = JsonUtil.parseObject(resString,FlowResult.class);
			
			return result;
		} catch (Exception e) {
			logger.error("请求app流量key业务异常", e);
		}
		result = new FlowResult();
		result.setCode(10001);
		result.setMsg("请求失败");
		return result;
	}
}

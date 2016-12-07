package com.shiliu.game.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	// 把JSON文本parse为JavaBean 
	public static final <T> T parseObject(String json, Class<T> clazz) {
		return (T) JSON.parseObject(json,clazz);
	}
	// 将JavaBean序列化为JSON文本 
	public static final String toJSONString(Object obj){
		return JSON.toJSONString(obj);
	}
	//把JSON文本parse成JavaBean集合
	public static final <T> List<T> parseArray(String text, Class<T> clazz){
		return JSON.parseArray(text, clazz);
	}
	// 把JSON文本parse为JSONObject或者JSONArray
	public static final Object parse(String text){
		return JSON.parse(text);
	}
	// 把JSON文本parse成JSONObject
	public static final JSONObject parseObject(String text){
		return JSON.parseObject(text);
	}
	// 把JSON文本parse成JSONArray
	public static final JSONArray parseArray(String text){
		return JSON.parseArray(text);
	}
	// 将JavaBean序列化为带格式的JSON文本 
	public static final String toJSONString(Object object, boolean prettyFormat){
		return JSON.toJSONString(object,prettyFormat);
	}
	// 将JavaBean转换为JSONObject或者JSONArray
	public static final Object toJSON(Object javaObject){
		return JSON.toJSON(javaObject);
	}
	//将集合转换为json字符串
	public static String encodeList(Collection<Object> objs) {
		JSONArray list = new JSONArray();
		if (objs == null || objs.size() == 0)
			return list.toString();
		for (Object ae : objs) {
			list.add(ae);
		}
		return list.toString();
	}
	 public static List<Map<String, Object>> parseJSON2List(String jsonStr){
	        JSONArray jsonArr = JSONArray.parseArray(jsonStr);
	        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	        Iterator<Object> it = jsonArr.iterator();
	        while(it.hasNext()){
	            JSONObject json2 = (JSONObject)it.next();
	            list.add(parseJSON2Map(json2.toString()));
	        }
	        return list;
	    }
	 public static Map<String, Object> parseJSON2Map(String jsonStr){
	        Map<String, Object> map = new HashMap<String, Object>();
	        //最外层解析
	        JSONObject json = JSONObject.parseObject(jsonStr);
	        for(Object k : json.keySet()){
	            Object v = json.get(k); 
	            //如果内层还是数组的话，继续解析
	            if(v instanceof JSONArray){
	                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	                Iterator<Object> it = ((JSONArray)v).iterator();
	                while(it.hasNext()){
	                    JSONObject json2 = (JSONObject)it.next();
	                    list.add(parseJSON2Map(json2.toString()));
	                }
	                map.put(k.toString(), list);
	            } else {
	                map.put(k.toString(), v);
	            }
	        }
	        return map;
	    }
}
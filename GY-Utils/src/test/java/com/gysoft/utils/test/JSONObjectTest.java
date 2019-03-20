package com.gysoft.utils.test;

import com.alibaba.fastjson.JSONObject;

public class JSONObjectTest {
	
	public static void main(String[] args) {
		String a = "{\"a\":\"null\"}";
		JSONObject a1 = new JSONObject();
		a1.toJSON(a);
		Long l = a1.getLong("a");
		System.out.println(l);
	}
}

package com.test;

import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) {
		String a = "{'name':'name'}";
		JSONObject j = JSONObject.parseObject(a);
		System.out.println(j.getString("name"));
	}
}

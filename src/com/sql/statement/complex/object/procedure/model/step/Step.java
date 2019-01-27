package com.sql.statement.complex.object.procedure.model.step;

import com.alibaba.fastjson.JSONObject;
import com.sql.statement.BasicModel;

/**
 * 步骤接口
 * @author DougLei
 */
public interface Step extends BasicModel{

	String getId();
	
	/**
	 * 设置step类型
	 * @param stepIndex
	 * @param type
	 */
	void setType(int stepIndex, Object type);

	/**
	 * 设置step的json内容
	 * @param stepIndex
	 * @param json
	 */
	void setJson(int stepIndex, JSONObject json);
	
	String getDesc();
	void setDesc(Object desc);
}

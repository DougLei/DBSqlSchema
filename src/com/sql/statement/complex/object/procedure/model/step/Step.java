package com.sql.statement.complex.object.procedure.model.step;

import com.alibaba.fastjson.JSONObject;
import com.sql.statement.basic.model.Basic;

/**
 * 步骤接口
 * @author DougLei
 */
public interface Step extends Basic{

	/**
	 * 设置step类型
	 * @param type
	 */
	void setType(String type);

	/**
	 * 设置step的json内容
	 * @param stepIndex
	 * @param json
	 */
	void setJson(int stepIndex, JSONObject json);
}

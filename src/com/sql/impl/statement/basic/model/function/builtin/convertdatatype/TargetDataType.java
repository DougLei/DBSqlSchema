package com.sql.impl.statement.basic.model.function.builtin.convertdatatype;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.datatype.DataType;
import com.sql.util.StrUtils;

/**
 * 要转换成的数据类型
 * @author DougLei
 */
class TargetDataType {
	private DataType dataType;
	private int length;
	private int precision;
	
	public TargetDataType(JSONObject json) {
		this.dataType = DataType.toValue(json.getString("dataType"));
		this.length = json.getIntValue("length");
		setPrecision(json.get("precision"));
	}
	private void setPrecision(Object precision) {
		if(StrUtils.isEmpty(precision)){
			precision = -1;
		}else{
			this.precision = Integer.valueOf(precision.toString());
		}
	}
}

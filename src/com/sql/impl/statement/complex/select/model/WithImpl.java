package com.sql.impl.statement.complex.select.model;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.complex.select.model.With;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class WithImpl extends BasicModelImpl implements With {

	private String alias;
	private String sqlStatement;// 存储的是处理后的select sql语句，再和alias整合
	
	public WithImpl(JSONObject json) {
		alias = json.getString("alias");
		if(StrUtils.isEmpty(alias)){
			throw new NullPointerException("with 子句的alias属性不能为空");
		}
	}

	protected String processSqlStatement() {
		return alias + " as (" + sqlStatement + ") ";
	}

	public With setSqlStatement(StringBuilder sqlStatement) {
		this.sqlStatement = sqlStatement.toString();
		return this;
	}
}

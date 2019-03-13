package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec;

import java.util.Arrays;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.Tools;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.impl.statement.complex.object.procedure.InOut;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareContext;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareEntity;
import com.sql.statement.basic.model.function.Function;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ExecParameter {
	private Type type;
	private String value;
	
	private boolean isDeclare;
	private JSONObject declareEntityJson;
	private String paramName;
	
	private Function valueFunction;
	
	private InOut in;
	
	public ExecParameter(JSONObject json) {
		this.type = Type.toValue(json.getString("type"));
		
		this.value = json.getString("value");
		
		this.isDeclare = json.getBooleanValue("isDeclare");
		this.declareEntityJson = json.getJSONObject("declare");
		if(declareEntityJson != null && StrUtils.notEmpty(declareEntityJson.getString("name"))){
			this.paramName = declareEntityJson.getString("name");
		}else{
			this.paramName = json.getString("paramName");
		}

		this.valueFunction = FunctionImpl.newInstance(json.getJSONObject("valueFunction"));
		
		String inout = json.getString("inOut");
		if(StrUtils.isEmpty(inout)){
			this.in = InOut.IN;
		}else{
			this.in = InOut.toValue(inout);
		}
	}
	
	public String getParamSqlStatement() {
		if(isDeclare){
			DeclareContext.recordDeclare(declareEntityJson);
		}
		switch(type){
			case VALUE:
				return value;
			case PARAMETER:
				return Tools.getName(null, paramName);
			case FUNCTION:
				return valueFunction.getSqlStatement();
		}
		return null;
	}
	
	/**
	 * 是否是参数类型
	 * @return
	 */
	public boolean isParameter(){
		return type == Type.PARAMETER;
	}
	
	/**
	 * 是否是输出参数
	 * @return
	 */
	public boolean isOutParameter() {
		return in == InOut.OUT && isParameter();
	}
	/**
	 * 是否是输入参数
	 * @return
	 */
	public boolean isInParameter() {
		return in == InOut.IN && isParameter();
	}
	/**
	 * 是否是输入输出参数
	 * @return
	 */
	public boolean isInOutParameter() {
		return in == InOut.INOUT && isParameter();
	}
	
	private enum Type{
		VALUE,
		PARAMETER,
		FUNCTION;
		
		static Type toValue(String str){
			try {
				return Type.valueOf(str.trim().toUpperCase());
			} catch (Exception e) {
				throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(Type.values())+"]");
			}
		}
		
		public String toString(){
			return name();
		}
	}

	/**
	 * 获取参数的数据类型的sql语句
	 * @return
	 */
	public String getDataTypeSql() {
		if(isParameter()){
			DeclareEntity declare = DeclareContext.getDeclareEntity(paramName);
			if(declare != null){
				StringBuilder sb = new StringBuilder(50);
				sb.append(declare.getBaseDataType());
				if(declare.getLength() > 0){
					sb.append("(").append(declare.getLength());
					if(declare.getPrecision() > -1){
						sb.append(", ").append(declare.getPrecision());
					}
					sb.append(")");
				}
				return sb.toString();
			}
		}
		throw new NullPointerException("没有获取到paramName为["+paramName+"]的DeclareEntity对象实例");
	}
}

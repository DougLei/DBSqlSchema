package com.sql.impl.statement.complex.object.procedure;


/**
 * 
 * @author DougLei
 */
public class SQLSERVER_ProcedureSqlStatementBuilderImpl extends ProcedureSqlStatementBuilderImpl {

	public String coverSqlServerSql(String procedureName) {
		StringBuilder sb = new StringBuilder(200);
		sb.append("if exists (select name from sysobjects where xtype = 'P' and name = N'").append(procedureName).append("')");
		sb.append(newline());
		sb.append("drop procedure ").append(procedureName);
		sb.append(newline());
		sb.append("go");
		sb.append(newline());
		return sb.toString();
	}

	public String coverOracleSql() {
		return "";
	}
	
//	protected String processParameter() {
//		List<ParameterEntity> parameterEntityList = getParameterEntityList();
//		if(parameterEntityList != null && parameterEntityList.size() > 0){
//			int size = parameterEntityList.size();
//			StringBuilder sb = new StringBuilder(size*50);
//			
//			ParameterEntity parameter = null;
//			for(int i=0;i<size;i++){
//				parameter = parameterEntityList.get(i);
//				if(parameter.isSupportAppendCustomSqlStatement()){
//					sb.append("@").append(parameter.getName()).append(" ");
//					
//					if(parameter.isBaseType()){
//						sb.append(parameter.getBaseDataType());
//						if(parameter.getLength() > 0){
//							sb.append("(").append(parameter.getLength());
//							if(parameter.getPrecision() > -1){
//								sb.append(", ").append(parameter.getPrecision());
//							}
//							sb.append(")");
//						}
//						if(StrUtils.notEmpty(parameter.getDefaultValue())){
//							sb.append("=").append(parameter.getDefaultValue());
//						}
//						if(parameter.isOUT() || parameter.isIN_OUT()){
//							sb.append(" ").append("output");
//						}
//					}else{
//						sb.append(parameter.getAppendCustomSqlStatement());
//					}
//					if(i<size-1){
//						sb.append(",");
//					}
//					sb.append(newline());
//				}
//				if(parameter.isCreateType()){
//					CreateTypeContext.recordCreateTypeSqlStatement(parameter.getCreateTypeSqlStatement());
//				}
//			}
//			return sb.toString();
//		}
//		return null;
//	}
}

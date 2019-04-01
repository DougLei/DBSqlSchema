package com.sql.impl.statement.basic.model.function.builtin.stuff.db;

import com.douglei.utils.StringUtil;
import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.stuff.Stuff;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE}, functionName = "_stuff")
public class ORACLE_Stuff extends Stuff{

	public String getSqlStatement() {
		if(startIndex != -1 && subLength != -1){
			return "substr("+source+",1,"+(startIndex-1)+")" +(StringUtil.isEmpty(replaceTarget)?"":"||"+replaceTarget+"||")+ "substr("+source+","+(startIndex+subLength)+")";
		}
		return "substr("+source+",1,("+startIndexParam+")-1)" +(StringUtil.isEmpty(replaceTarget)?"":"||"+replaceTarget+"||")+ "substr("+source+",("+startIndexParam+")+("+subLengthParam+"))";
	}
}

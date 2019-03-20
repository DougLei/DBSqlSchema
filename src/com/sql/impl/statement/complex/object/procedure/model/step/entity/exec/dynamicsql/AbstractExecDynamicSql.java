package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.dynamicsql;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.ExecParameter;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractExecDynamicSql extends LogicEntity{
	protected DynamicSqlEntity dynamicSqlEntity;
	protected List<ExecParameter> execParameterList;
	
	public AbstractExecDynamicSql(DynamicSqlEntity dynamicSqlEntity, ConditionEntity condition, List<ExecParameter> execParameterList) {
		super(condition, false);
		this.dynamicSqlEntity = dynamicSqlEntity;
		this.execParameterList = execParameterList;
	}

	protected StringBuilder executeDynamicSql = new StringBuilder();
	/**
	 * 处理执行动态sql语句，保存最终语句到executeDynamicSql属性中
	 */
	protected abstract void processExecuteDynamicSql();
}

package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.proc;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.ExecParameter;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractExecProc extends LogicEntity{
	protected String procedureName;
	protected List<ExecParameter> execParameterList;
	
	public AbstractExecProc(List<ConditionGroup> groupList, String procedureName, List<ExecParameter> execParameterList) {
		super(groupList, false);
		this.procedureName = procedureName;
		this.execParameterList = execParameterList;
	}
	
	protected StringBuilder executeProcedureSql = new StringBuilder();
	/**
	 * 处理存储过程的sql语句，保存最终语句到executeProcedureSql属性中
	 */
	protected abstract void processExecuteProcedureSql();
}

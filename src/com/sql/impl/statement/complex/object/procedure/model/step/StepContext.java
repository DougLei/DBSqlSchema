package com.sql.impl.statement.complex.object.procedure.model.step;

import java.util.HashMap;
import java.util.Map;

import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;

/**
 * 
 * @author DougLei
 */
public class StepContext {
	private static final ThreadLocal<Map<String, StepEntity>> localStepEntityCache = new ThreadLocal<Map<String, StepEntity>>();
	
	public static void clear(){
		Map<String, StepEntity> stepEntityCache = localStepEntityCache.get();
		if(stepEntityCache != null){
			stepEntityCache.clear();
		}
	}
	
	public static StepEntity getStepEntity(String id){
		Map<String, StepEntity> stepEntityCache = localStepEntityCache.get();
		if(stepEntityCache != null){
			return stepEntityCache.get(id);
		}
		return null;
	}
	
	public static void putStepEntityCache(StepEntity stepEntity){
		String id = stepEntity.getStepId();
		
		Map<String, StepEntity> stepEntityCache = localStepEntityCache.get();
		if(stepEntityCache == null){
			stepEntityCache = new HashMap<String, StepEntity>();
			localStepEntityCache.set(stepEntityCache);
		}else{
			if(stepEntityCache.containsKey(id)){
				throw new IllegalArgumentException("step出现重复的id值["+id+"]，请检查配置文件");
			}
		}
		stepEntityCache.put(id, stepEntity);
	}
}

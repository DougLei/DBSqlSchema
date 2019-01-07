package com.sql.exception;

/**
 * 不支持的数据库类型
 * @author DougLei
 */
public class UnsupportDatabaseType extends DBSqlSchemaException {
	private static final long serialVersionUID = 5258110834054061242L;
	
	public UnsupportDatabaseType() {
		super();
	}
	public UnsupportDatabaseType(String message) {
		super(message);
	}
	public UnsupportDatabaseType(String message, Throwable cause) {
		super(message, cause);
	}
}

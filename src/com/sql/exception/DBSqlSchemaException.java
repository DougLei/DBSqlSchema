package com.sql.exception;

/**
 * 
 * @author DougLei
 */
public class DBSqlSchemaException extends RuntimeException {
	private static final long serialVersionUID = 7014301392030178424L;
	
	public DBSqlSchemaException() {
		super();
	}
	public DBSqlSchemaException(String message) {
		super(message);
	}
	public DBSqlSchemaException(String message, Throwable cause) {
		super(message, cause);
	}
}

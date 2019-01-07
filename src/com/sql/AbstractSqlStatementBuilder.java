package com.sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.sql.enums.DatabaseTypeEnum;
import com.sql.enums.EncodingEnum;
import com.sql.util.IOUtil;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractSqlStatementBuilder implements SqlStatementBuilder{

	protected DatabaseTypeEnum databaseType;
	protected EncodingEnum encoding;
	protected String jsonConfig;
	
	public void setDatabaseType(DatabaseTypeEnum databaseType) {
		this.databaseType = databaseType;
	}
	public void setEncoding(EncodingEnum encoding) {
		this.encoding = encoding;
	}

	public void setJsonConfig(String filePath) {
		if(StrUtils.isEmpty(filePath)){
			throw new NullPointerException("filePath参数不能为空");
		}
		setJsonConfig(new File(filePath));
	}

	public void setJsonConfig(File file) {
		if(!file.exists()){
			throw new NullPointerException("不存在路径为["+file.getAbsolutePath()+"]的文件");
		}
		if(file.isDirectory()){
			throw new IllegalArgumentException("["+file.getAbsolutePath()+"]路径下为文件夹对象，非文件对象");
		}
		try {
			jsonConfig = IOUtil.readerToString(new FileReader(file), file.getName());
		} catch (FileNotFoundException e) {
		}
	}
	
	public void setJsonConfig(byte[] byteArray) {
		if(byteArray == null || byteArray.length == 0){
			throw new NullPointerException("jsonConfigByteArray参数不能为空");
		}
		jsonConfig = StrUtils.getStringByByteArray(byteArray, encoding);
	}
}

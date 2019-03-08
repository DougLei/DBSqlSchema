package com.sql.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 流工具类
 * @author DougLei
 */
public class IOUtil {
	
	/**
	 * inputstream 转 byte数组
	 * @param inputStream
	 * @param inputStreamName
	 * @return
	 */
	public static byte[] inputStreamToByteArray(InputStream inputStream, String inputStreamName) {
		if(inputStream == null){
			throw new NullPointerException("inputStream [" + inputStreamName + "] 不能为空");
		}
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[16 * 1024];
		try {
			int bytesRead = inputStream.read(buffer);
			while (bytesRead != -1) {
				outputStream.write(buffer, 0, bytesRead);
				bytesRead = inputStream.read(buffer);
			}
			return outputStream.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("读取inputStream ["+inputStreamName+"] 出现异常", e);
		} finally{
			CloseUtil.closeIO(inputStream, outputStream);
		}
	}
	
	/**
	 * reader 转 string
	 * @param reader
	 * @param readerName
	 * @return
	 */
	public static String readerToString(Reader reader, String readerName){
		if(reader == null){
			throw new NullPointerException("reader [" + readerName + "] 不能为空");
		}
		int len = 0;
		Writer writer = new StringWriter();
		char[] cbuf = new char[2 * 1024];
		try {
			if(reader.ready()){
				while((len=reader.read(cbuf)) != -1){
					writer.write(cbuf, 0, len);
				}
				return writer.toString();
			}
			return null;
		} catch (IOException e) {
			throw new IllegalArgumentException("读取reader ["+readerName+"] 出现异常", e);
		} finally{
			CloseUtil.closeIO(reader, writer);
		}
	}
}

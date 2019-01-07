package com.sql.enums;

/**
 * 编码格式
 * @author DougLei
 */
public enum EncodingEnum {
	
	ISO8859_1("iso8859-1"),
	UTF_8("utf-8"),
	GBK("gbk"),
	GB2312("gb2312");
	
	private String encoding;
	private EncodingEnum(String encoding) {
		this.encoding = encoding;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}

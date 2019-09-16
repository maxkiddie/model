package com.ydy.constant;

import com.ydy.exception.DataNotFoundException;
import com.ydy.ienum.EnumTable;

public enum MysqlColumnTypeEnum {
	TINYINT("Integer"), SMALLINT("Integer"), MEDIUMINT("Integer"), INT("Integer"), INTEGER("Integer"), BIGINT("Long"),
	FLOAT("Float"), DOUBLE("Double"), DECIMAL("Double"), DATE("Date"), TIME("Date"), YEAR("Integer"), DATETIME("Date"),
	TIMESTAMP("Date"), CHAR("String"), VARCHAR("String"), TINYBLOB("String"), TINYTEXT("String"), BLOB("String"),
	TEXT("String"), MEDIUMBLOB("String"), MEDIUMTEXT("String"), LONGBLOB("String"), LONGTEXT("String");

	private String type;

	private MysqlColumnTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String to(String type) {
		if (type == null || "".equals(type)) {
			return "";
		}
		for (MysqlColumnTypeEnum data : MysqlColumnTypeEnum.values()) {
			if (type.toUpperCase().equals(data.toString())) {
				return data.getType();
			}
		}
		throw new DataNotFoundException(EnumTable.DATA_NOT_FOUND);
	}

}

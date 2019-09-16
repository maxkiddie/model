/**
 * 
 */
package com.ydy.model;

import javax.persistence.Table;

/**
 * @author xuzhaojie column_name,is_nullable,data_type,column_comment 2019年5月29日
 *         上午8:31:37
 */
@Table(name = "information_schema.columns")
public class TableColumns {

	private String tableSchema;
	private String tableName;
	private String columnName;
	private String isNullable;
	private String dataType;
	private String columnComment;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}

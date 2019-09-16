/**
 * 
 */
package com.ydy.model;

import javax.persistence.Table;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 上午8:31:37
 */
@Table(name = "information_schema.tables")
public class TableInfo {

	private String tableSchema;
	private String tableName;
	private String tableComment;

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

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

}

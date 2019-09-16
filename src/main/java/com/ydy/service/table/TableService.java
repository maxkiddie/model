package com.ydy.service.table;

import java.util.List;

import com.ydy.model.TableColumns;
import com.ydy.model.TableInfo;

public interface TableService {

	List<TableInfo> list(String tableSchema);

	List<TableColumns> listColumns(String tableSchema, String tableName);

	String toJavaModel(List<TableColumns> list);
}

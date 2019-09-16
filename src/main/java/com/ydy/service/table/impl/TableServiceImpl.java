package com.ydy.service.table.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ydy.constant.MysqlColumnTypeEnum;
import com.ydy.exception.BusinessException;
import com.ydy.ienum.EnumTable;
import com.ydy.mapper.TableColumnsMapper;
import com.ydy.mapper.TableInfoMapper;
import com.ydy.model.TableColumns;
import com.ydy.model.TableInfo;
import com.ydy.service.table.TableService;
import com.ydy.utils.CharUtil;

@Service
@Transactional
public class TableServiceImpl implements TableService {

	@Autowired
	private TableInfoMapper infoMapper;
	@Autowired
	private TableColumnsMapper columnsMapper;

	@Override
	public List<TableInfo> list(String tableSchema) {
		TableInfo info = new TableInfo();
		info.setTableSchema(tableSchema);
		return infoMapper.select(info);
	}

	@Override
	public List<TableColumns> listColumns(String tableSchema, String tableName) {
		TableColumns columns = new TableColumns();
		columns.setTableSchema(tableSchema);
		columns.setTableName(tableName);
		return columnsMapper.select(columns);
	}

	@Override
	public String toJavaModel(List<TableColumns> list) {
		if (CollectionUtils.isEmpty(list)) {
			throw new BusinessException(EnumTable.COLUMNS_LIST_EMPTY);
		}
		StringBuilder builder = new StringBuilder();
		for (TableColumns columns : list) {
			builder.append("private ");
			builder.append(MysqlColumnTypeEnum.to(columns.getDataType()) + " ");
			builder.append(CharUtil.lineToHump(columns.getColumnName()));
			builder.append(";//");
			builder.append(columns.getColumnComment());
			builder.append("\n");
		}
		System.out.println(builder.toString());
		return builder.toString();
	}

}

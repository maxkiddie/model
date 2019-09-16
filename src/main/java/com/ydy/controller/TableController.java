package com.ydy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydy.annotation.CtrlParam;
import com.ydy.model.TableColumns;
import com.ydy.model.TableInfo;
import com.ydy.service.table.TableService;

@RestController
@RequestMapping("table")
public class TableController {

	@Autowired
	private TableService tableService;

	@GetMapping("listTable")
	public ResponseEntity<List<TableInfo>> listTable(@CtrlParam String tableSchema) {
		return ResponseEntity.ok(tableService.list(tableSchema));
	}

	@GetMapping("listColumns")
	public ResponseEntity<List<TableColumns>> listColumns(@CtrlParam String tableSchema, @CtrlParam String tableName) {
		return ResponseEntity.ok(tableService.listColumns(tableSchema, tableName));
	}

	@GetMapping("javaModel")
	public ResponseEntity<String> javaModel(@CtrlParam String tableSchema, @CtrlParam String tableName) {
		return ResponseEntity.ok(tableService.toJavaModel(tableSchema, tableName));
	}
}

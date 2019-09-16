/**
 * 
 */
package com.ydy.task.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ydy.task.TaskService;

/**
 * @author xuzhaojie
 *
 *         2019年3月5日 上午11:25:29
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Value("${NOT_PAY_TIME_LONG:14d0h0m0s}")
	private String NOT_PAY_TIME_LONG;

	@Value("${FILE_DEL_TIME_LONG:30d0h0m0s}")
	private String FILE_DEL_TIME_LONG;

	@Value("${PACK_DEL_TIME_LONG:14d0h0m0s}")
	private String PACK_DEL_TIME_LONG;

	@Scheduled(cron = "${CLOSE_ORDER_TASK:0 0/10 * * * ?}")
	public void orderClose() {
	}

	@Override
	@Scheduled(cron = "${FILE_DEL_TASK:0 0 0 * * ?}")
	public void fileDel() {
	}

	@Override
	@Scheduled(cron = "${PACK_DEL_TASK:0 0 0 * * ?}")
	public void packDel() {
	}
}

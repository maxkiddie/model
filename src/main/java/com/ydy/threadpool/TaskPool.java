package com.ydy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskPool {

	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	
	public static void execute(Runnable command)
	{
		cachedThreadPool.execute(command);;
	}
}

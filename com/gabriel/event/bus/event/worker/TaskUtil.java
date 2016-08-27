package com.gabriel.event.bus.event.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskUtil {
	public static ExecutorService pooled = Executors.newCachedThreadPool();
	
	public static void pooled(Runnable task){
		pooled.submit(task);
	}
}

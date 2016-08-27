package com.gabriel.event.bus.event.worker;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class QueuedWorkerRunnable<T> implements Runnable {
	protected LinkedBlockingQueue<T> blockingQueue;
	protected boolean closed;
	
	public QueuedWorkerRunnable(){
		this.blockingQueue = new LinkedBlockingQueue<T>();
		closed = false;
		TaskUtil.pooled(this);
	}
	
	public boolean add(T t) {
		return blockingQueue.add(t);
	}
	
	public void run(){
		while(!closed){
			T t = null;
			try {
				t = blockingQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(t == null){
				continue;
			}
			process(t);
		}
	}
	
	public abstract void process(T t);
}

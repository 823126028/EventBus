package com.gabriel.event.bus.event.worker;

import com.gabriel.event.bus.event.comm.IEvent;
import com.gabriel.event.bus.register.RegisterManager;

public class EventQueueWorker extends QueuedWorkerRunnable<IEvent> {
	public RegisterManager registerManager;
	
	public EventQueueWorker(RegisterManager registerManager){
		super();
		this.registerManager = registerManager;
	}
	
	@Override
	public void process(IEvent event) {
		registerManager.handleInterest(event.getType().getEventType(), event);
	}
	
	public void close(){
		super.blockingQueue.clear();
		super.closed = true;
	}
}

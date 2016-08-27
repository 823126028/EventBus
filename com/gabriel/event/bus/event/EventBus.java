package com.gabriel.event.bus.event;

import com.gabriel.event.bus.event.comm.IEvent;
import com.gabriel.event.bus.event.worker.EventQueueWorker;
import com.gabriel.event.bus.exception.EventBusNotStartException;
import com.gabriel.event.bus.register.IRegister;
import com.gabriel.event.bus.register.RegisterManager;

public class EventBus {
	private final static EventBus instance = new EventBus();
	private RegisterManager registerManager = new RegisterManager();
	private EventQueueWorker eventQueueWorker;
	
	
	private volatile boolean started = false;
	
	private EventBus(){};
	
	public static EventBus getInstance(){
		return instance;
	}
	
	public void register(String interest,IRegister register){
		registerManager.register(interest,register);
	}
	
	public void unRegisterAll(IRegister register){
		registerManager.unRegisterAll(register);
	}
	
	public void unRegister(String interest,IRegister register){
		registerManager.unRegister(interest, register);
	}
	
	public void isValid() throws EventBusNotStartException{
		if(!started){
			throw new EventBusNotStartException("event bus manager not started");
		}
	}
	
	public void close(){
		started = false;
		eventQueueWorker.close();
		registerManager.close();
	}
	
	public void init(){
		if(started)
			return;
		started = true;
		eventQueueWorker = new EventQueueWorker(registerManager);
	}
	
	
	public boolean sendEvent(IEvent event) throws EventBusNotStartException{
		isValid();
		return eventQueueWorker.add(event);
	}	
}

package com.gabriel.event;

import com.gabriel.event.bus.event.EventBus;
import com.gabriel.event.bus.event.comm.CloseEvent;
import com.gabriel.event.bus.event.comm.CreateEvent;
import com.gabriel.event.bus.event.comm.IEvent;
import com.gabriel.event.bus.event.comm.EventTypeConstants.EventType;
import com.gabriel.event.bus.exception.EventBusNotStartException;
import com.gabriel.event.bus.register.IRegister;

public class Main {
	
	
	public static void main(String[] args){
		EventBus.getInstance().init();
		IRegister register = new IRegister() {
			@Override
			public void onEvent(IEvent event) {
				System.out.println(event.getType());
			}
		};
		EventBus.getInstance().register(EventType.CREATE_EVENT.getEventType(), register);
		Thread test = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i = 0; i <= 20; i++){
						IEvent event;
						if(i % 2 == 0){
							event = new CreateEvent();
						}else{
							event = new CloseEvent();
						}
						EventBus.getInstance().sendEvent(event);
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} catch (EventBusNotStartException e) {
					e.printStackTrace();
				}
			}
		});
		test.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		EventBus.getInstance().unRegister(EventType.CREATE_EVENT.getEventType(), register);
		EventBus.getInstance().register(EventType.CLOSE_EVENT.getEventType(), register);
	}
}

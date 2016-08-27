package com.gabriel.event.bus.register;
import com.gabriel.event.bus.event.comm.IEvent;

public interface IRegister {
	public void onEvent(IEvent event);
}

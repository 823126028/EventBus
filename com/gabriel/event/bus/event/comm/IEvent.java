package com.gabriel.event.bus.event.comm;

import com.gabriel.event.bus.event.comm.EventTypeConstants.EventType;

public interface IEvent {
	public EventType getType();
}

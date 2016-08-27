package com.gabriel.event.bus.event.comm;

import com.gabriel.event.bus.event.comm.EventTypeConstants.EventType;

public class CloseEvent implements IEvent{
	@Override
	public EventType getType() {
		return EventTypeConstants.EventType.CLOSE_EVENT;
	}
}

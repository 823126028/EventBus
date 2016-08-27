package com.gabriel.event.bus.event.comm;

import com.gabriel.event.bus.event.comm.EventTypeConstants.EventType;

public class CreateEvent implements IEvent{
	@Override
	public EventType getType() {
		return EventTypeConstants.EventType.CREATE_EVENT;
	}
}

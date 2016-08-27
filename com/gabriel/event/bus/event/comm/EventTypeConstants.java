package com.gabriel.event.bus.event.comm;

public class EventTypeConstants {
	public enum EventType{
		CREATE_EVENT("create_event"),
		CLOSE_EVENT("close_event");
		
		private String type;
		EventType(String type){
			this.type = type;
		}
		public String getEventType(){
			return this.type;
		}
	}
}

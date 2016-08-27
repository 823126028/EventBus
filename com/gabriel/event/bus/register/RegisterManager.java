package com.gabriel.event.bus.register;
import java.util.concurrent.ConcurrentHashMap;

import com.gabriel.event.bus.event.comm.IEvent;
import com.gabriel.event.bus.register.util.ConcurrentHashSet;

public class RegisterManager {
	private ConcurrentHashMap<String,ConcurrentHashSet<IRegister>> registerMap = new ConcurrentHashMap<String,ConcurrentHashSet<IRegister>>();
	
	public void close(){
		registerMap.clear();
	}

	public void register(String interest,IRegister register){
		ConcurrentHashSet<IRegister> registerSet = registerMap.get(interest);
		if(registerSet == null){
			ConcurrentHashSet<IRegister> newSet = new ConcurrentHashSet<IRegister>();
			ConcurrentHashSet<IRegister> oldSet = registerMap.putIfAbsent(interest, newSet);
			if(oldSet != null){
				registerSet = oldSet;
			}else{
				registerSet = newSet;
			}
		}
		registerSet.add(register);
	}

	
	public void unRegister(String interest,IRegister register){
		ConcurrentHashSet<IRegister> registerSet = registerMap.get(interest);
		if(registerSet == null || registerSet.size() == 0){
			return;
		}
		registerSet.remove(register);
	}
	
	public void unRegisterAll(IRegister register){
		for (ConcurrentHashSet<IRegister> registerSet : registerMap.values()) {
			registerSet.remove(register);
		}
	}
	
	public void handleInterest(String interest,IEvent event){
		ConcurrentHashSet<IRegister> registerSet = registerMap.get(interest);
		if(registerSet == null){
			return;
		}
		for (IRegister register : registerSet.keySet()) {
			register.onEvent(event);
		}
	}
	
	
}

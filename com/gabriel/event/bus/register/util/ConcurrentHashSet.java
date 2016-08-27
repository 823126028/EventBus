package com.gabriel.event.bus.register.util;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

public class ConcurrentHashSet<T>{
	private ConcurrentHashMap<T,Boolean> _innerMap;
	
	public ConcurrentHashSet(){
		_innerMap = new ConcurrentHashMap<T,Boolean>();
	}
	
	public boolean contains(T t){
		return _innerMap.containsKey(t);
	}
	
	public void add(T t){
		_innerMap.put(t, true);
	}
	
	public void remove(T t){
		_innerMap.remove(t);
	}
	
	public void clear(){
		_innerMap.clear();
	}
	
	public int size(){
		return _innerMap.size();
	}
	
	public KeySetView<T, Boolean> keySet(){
		return _innerMap.keySet();
	}

}

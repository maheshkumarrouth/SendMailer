package com.cultuzz.utilities;

import org.springframework.stereotype.Component;

@Component
public class UtilityClass {
	
	private Byte activeThreads=0;
	private Byte maxThreads=30;
	
	public Byte getActiveThreads() {
		return activeThreads;
	}
	public void setActiveThreads(Byte activeThreads) {
		this.activeThreads = activeThreads;
	}
	public Byte getMaxThreads() {
		return maxThreads;
	}
	public void setMaxThreads(Byte maxThreads) {
		this.maxThreads = maxThreads;
	}
	
}

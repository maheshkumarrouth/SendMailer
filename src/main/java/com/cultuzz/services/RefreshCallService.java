package com.cultuzz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cultuzz.dao.interfaces.RefreshDataQueueDAO;
import com.cultuzz.handlers.RefreshDataHandler;
import com.cultuzz.models.RefreshDataQueue;

public class RefreshCallService implements Runnable{

	private RefreshDataQueue refreshDataQueue = null;
	
	@Autowired
	private RefreshDataHandler refreshDataHandler;
	
	@Autowired
	private RefreshDataQueueDAO refreshDataQueueDAO;
	
	public RefreshCallService(RefreshDataQueue refreshDataQueue) {
		this.refreshDataQueue = refreshDataQueue;
	}
	
	public RefreshCallService() {
		
	}

	@Override
	public void run() {
		
		refreshDataHandler.sendRefreshDataToCore(refreshDataQueue.getObjectID());
		
		refreshDataQueueDAO.deleteOject(refreshDataQueue);
	}

}

package com.cultuzz.dao.interfaces;

import java.util.List;

import com.cultuzz.models.RefreshDataQueue;

public interface RefreshDataQueueDAO {
	public RefreshDataQueue findByID();
	public void updateData(RefreshDataQueue refreshDataQueue);
	public List findConnectedObjectsByObjectID(Integer cltzObjectID);
	public void deleteOject(RefreshDataQueue refreshDataQueue);
}

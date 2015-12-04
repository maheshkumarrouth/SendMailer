package com.cultuzz.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cultuzz.models.RefreshDataQueue;

public class RefreshDataQueueRowMapper implements RowMapper<RefreshDataQueue>{

	@Override
	public RefreshDataQueue mapRow(ResultSet arg0, int arg1)
			throws SQLException {
		RefreshDataQueue refreshDataQueue = new RefreshDataQueue();
		refreshDataQueue.setId(arg0.getInt(1));
		refreshDataQueue.setObjectID(arg0.getInt(2));
		refreshDataQueue.setStatus(arg0.getByte(3));
		refreshDataQueue.setIntime(arg0.getDate(4));
		refreshDataQueue.setOuttime(arg0.getDate(5));
		refreshDataQueue.setEndtime(arg0.getDate(6));
		return refreshDataQueue;
	}

}

package com.cultuzz.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cultuzz.models.Distributor;

public class DistributorRowMapper implements RowMapper<Distributor>{

	@Override
	public Distributor mapRow(ResultSet arg0, int arg1) throws SQLException {
		Distributor distributor = new Distributor();
		distributor.setCa_channel_id(arg0.getShort(1));
		distributor.setCs_distributor_id(arg0.getInt(2));
		return distributor;
	}

}

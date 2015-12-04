package com.cultuzz.dao.impls;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cultuzz.dao.interfaces.RefreshDataQueueDAO;
import com.cultuzz.dao.mappers.DistributorRowMapper;
import com.cultuzz.dao.mappers.RefreshDataQueueRowMapper;
import com.cultuzz.models.RefreshDataQueue;
@Component
public class RefreshDataQueueDAOImpl implements RefreshDataQueueDAO{
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Override
	public RefreshDataQueue findByID() {
		try{
			List list = jdbctemplate.query("select * from Iq.RefreshDataQueue where status=0 order by id limit 1", new RefreshDataQueueRowMapper());
			return (RefreshDataQueue)list.get(0);
		}catch(Exception e){
			//e.printStackTrace();
		}
		return null;
	}
	
	public void updateData(RefreshDataQueue refreshDataQueue){
		try{
			System.out.println("refreshDataQueue.getId()"+refreshDataQueue.getId());
			 // define query arguments
			Object[] params = {refreshDataQueue.getId()};
			// define SQL types of the arguments
			int[] types = {Types.INTEGER};
			jdbctemplate.update("update Iq.RefreshDataQueue set status=1 where id=?",params,types);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List findConnectedObjectsByObjectID(Integer cltzObjectID){
		try{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("select ca.* from CultAgent.objekt_x_channel oxc ");
			stringBuilder.append("join CultAgent.ca_channel_x_cs_distributor ca on ca_channel_id = oxc.channel_id ");
			stringBuilder.append(" where cusebeda_objekt_id=? and deleted=0");
			System.out.println("stringBuilder.toString()"+stringBuilder.toString());
			List list = jdbctemplate.query(stringBuilder.toString(), new DistributorRowMapper(),cltzObjectID);
			System.out.println("Debug @ stmt :: list"+list);
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}catch (Error e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteOject(RefreshDataQueue refreshDataQueue){
		try{
			// define query arguments
			Object[] params = {refreshDataQueue.getId()};
			// define SQL types of the arguments
			int[] types = {Types.INTEGER};
			jdbctemplate.update("delete from Iq.RefreshDataQueue where id=?",params,types);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

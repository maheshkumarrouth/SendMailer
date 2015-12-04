package com.cultuzz.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.cultuzz.dao.interfaces.RefreshDataQueueDAO;
import com.cultuzz.models.RefreshDataQueue;
import com.cultuzz.services.RefreshCallService;
import com.cultuzz.utilities.ExecutorServiceInstance;
import com.cultuzz.utilities.UtilityClass;

@Configuration
@EnableScheduling
public class LoadGenerateScheduler {
	
	@Autowired 
	private UtilityClass utilityClass;
	
	@Autowired 
	private ExecutorServiceInstance executorServiceInstance;
	
	@Autowired
	private RefreshDataQueueDAO refreshDataQueueDAO;
	
	@Autowired
	private ApplicationContext ap;
	
	@Scheduled(fixedRate=1000)
	public void doGenerateLoad(){
		int i=0;
		while(i<=15 && utilityClass.getActiveThreads()<=utilityClass.getMaxThreads()){
			RefreshDataQueue refreshDataQueue = refreshDataQueueDAO.findByID();
			if(refreshDataQueue != null){
				refreshDataQueueDAO.updateData(refreshDataQueue);
				RefreshCallService refreshCallService = new RefreshCallService(refreshDataQueue);
				ap.getAutowireCapableBeanFactory().autowireBean(refreshCallService);
				executorServiceInstance.getExecutorService().submit(refreshCallService);
			}
		}
	}
}

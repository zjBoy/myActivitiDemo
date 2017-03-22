package com.king.controller.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

public class ActivitiController {

	/**
	 * 发布流程
	 * @return
	 */
	public String deployActiviti(){
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
		        .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
		        .setJdbcUsername("sa")
		        .setJdbcPassword("")
		        .setJdbcDriver("org.h2.Driver")
		        .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		    ProcessEngine processEngine = cfg.buildProcessEngine();
		
		return "";
	}
}

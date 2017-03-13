package com.king.services;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service 
public class ActivitiService {
	@Autowired
    public  Environment env;
	public  ProcessEngine getProcessEngine(){
		String jdbcDriver = env.getProperty("spring.datasource.driver-class-name");
		String jdbcUrl = env.getProperty("spring.datasource.url");
		String jdbcUsername = env.getProperty("spring.datasource.username");
		String jdbcUPassword = env.getProperty("spring.datasource.password");
		//创建流程引擎配置对象
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		//设置数据库的驱动
		processEngineConfiguration.setJdbcDriver(jdbcDriver);
		processEngineConfiguration.setJdbcUrl(jdbcUrl);
		processEngineConfiguration.setJdbcUsername(jdbcUsername);
		processEngineConfiguration.setJdbcPassword(jdbcUPassword);
		//数据库的建表策略
		/*DB_SCHEMA_UPDATE_FALSE = "false";如果数据库里面没有表，自动为我们建立activiti的23张表， 如果有就会抛异常
		  "create-drop";当流程引擎被创建时会自动建表,如果流程引擎关闭会删掉表
		 "true"如果数据库里面没有表会自动建表，如果有表就不建立，如果activiti的sql脚本有更新，那么流程引擎再次建立时会更新数据库的表（要用的）
		 */
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//创建流程引擎对象
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		return processEngine;
	}
}

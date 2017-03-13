package com.king.controller.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.king.services.ActivitiService;

@RestController
public class ActivitiController {
	@Autowired
	public ActivitiService as;
	@Autowired
	ProcessEngine processEngine;
	@Autowired
	RepositoryService repositoryService;
	/**
	 * 发布流程
	 * @return
	 */
	public String deployActiviti(){
		//创建部署对象
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		//加载流程的配置文件和图片
		deploymentBuilder.addClasspathResource("diagrams/leave.bpmn")
						 .name("请假流程")
						 .category("请假")
						 .addClasspathResource("diagrams/leave.png");
		//部署流程
		deploymentBuilder.deploy();
		return "";
	}
	/**
	 * 使用java代码初始化环境
	 */
	 @RequestMapping(value="/init")
	public String initContextWithCode(Model model) {
		 System.out.println(processEngine);
		 deployActiviti();
		return "menu";
	}
}

package com.king.controller.activiti;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.king.bean.LeaveBill;
import com.king.services.ActivitiService;

import utils.ErrorUtil;

@Controller
public class ActivitiController {
	@Autowired
	public ActivitiService as;
	@Autowired
	ProcessEngine processEngine;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	/**
	 * 发布流程
	 * @return
	 */
	 @RequestMapping(value="/deploy",method=RequestMethod.POST)
	 @ResponseBody
	public  String deployActiviti(){
		//创建部署对象
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		//加载流程的配置文件和图片
		deploymentBuilder.addClasspathResource("diagrams/leave.bpmn")
						 .name("请假流程")
						 .category("请假")
						 .addClasspathResource("diagrams/leave.png");
		//部署流程
		deploymentBuilder.deploy();
		return ErrorUtil.create("0000", "发布成功").toJSONString();
	}
	public void startProcess() {
		runtimeService.startProcessInstanceByKey("leaveBill");
	} 
	 /**
	  * 申请请假
	  */
	 @ResponseBody
	 @RequestMapping(value="/leaveBillApply",method=RequestMethod.POST)
	 public String leaveBillApply(@RequestBody LeaveBill leaveBill) {
		 //启动一个流程
		 ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leaveBill");
		 String executionId = processInstance.getProcessInstanceId();
		 //提交请假
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("userName", leaveBill.getUserName());
		 map.put("days", leaveBill.getDays());
		 map.put("reason", leaveBill.getReason());
		 Task task=taskService.createTaskQuery()
		 			.executionId(executionId)
		 			.singleResult();
		 taskService.complete(task.getId(),map);
		 return ErrorUtil.create("0000", "提交申请成功").toJSONString();
	 } 
	/**
	 * 使用java代码初始化环境
	 */
	 @RequestMapping(value="/init")
	public String initContextWithCode(Model model) {
		 System.out.println(processEngine);
		return "menu";
	}
	 /**
	  * 跳转到我要请假页面
	  */
	 @RequestMapping(value="/askForLeave")
	 public String askForLeave() {
		 return "askForLeave";
	 }
	 /**
	  * 我的待审批任务
	  */
	 @RequestMapping(value="/myTaskList")
	 public String myTaskList() {
		 return "myTaskList";
	 }
}

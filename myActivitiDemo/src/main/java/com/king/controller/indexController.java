package com.king.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.king.controller.activiti.ActivitiController;


@Controller
public class indexController {
	 @RequestMapping("/")
	public String index(Model model){
		model.addAttribute("welcome","欢迎大家来听我分享工作流知识");
		return "index";
	}
}

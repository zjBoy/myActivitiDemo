package com.king.controller.apply;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ApplyController {
	 @RequestMapping("/hello/{name}")
	public String index(@PathVariable("name") String name, Model model){
		model.addAttribute("name",name);
		return "hello";
	}
}

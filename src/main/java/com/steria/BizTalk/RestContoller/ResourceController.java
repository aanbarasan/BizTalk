package com.steria.BizTalk.RestContoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResourceController {

	@RequestMapping(value = "/")
	public ModelAndView resource() {
		
		return new ModelAndView("home");
	}

}

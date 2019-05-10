package com.steria.BizTalk.RestContoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@RequestMapping(value = "/api/search", method = RequestMethod.POST)
	public String search() {

		return "check";
	}
}

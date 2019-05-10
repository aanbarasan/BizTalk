package com.steria.BizTalk.RestContoller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@RequestMapping(value = "/api/search", method = RequestMethod.POST)
	public HashMap<String, Integer> search(@RequestBody HashMap<Object, Object >data) {

		String start = data.get("start").toString();
		String end = data.get("end").toString();
		HashMap<String, Integer> map = new HashMap<>();
		map.put("total", 34);
		map.put("unique", 20);
		map.put("suspended", 5);
		return map;
	}
}

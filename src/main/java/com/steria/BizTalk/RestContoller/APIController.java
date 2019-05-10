package com.steria.BizTalk.RestContoller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@RequestMapping(value = "/api/search", method = RequestMethod.POST)
	public HashMap<String, Integer> search(@RequestParam("data") Object data ) {

		HashMap<String, Integer> map = new HashMap<>();
		map.put("total", 34);
		map.put("unique", 34);
		map.put("suspended", 34);
		return map;
	}
}

package com.steria.BizTalk.RestContoller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.steria.BizTalk.service.TripViewServices;

@RestController
public class ProcessedViewController {

	@Autowired
	TripViewServices tripviewServices;

	@RequestMapping(value = "/api/processview", method = RequestMethod.POST)
	public HashMap<String, Object> search(@RequestBody HashMap<Object, Object> data) throws ParseException {

		String start = data.get("start").toString();
		String end = data.get("end").toString();
		String type = data.get("type").toString();

		HashMap<String, Object> map = (HashMap<String, Object>) tripviewServices.processedViewTrip(start, end, type);
		return map;
	}
}

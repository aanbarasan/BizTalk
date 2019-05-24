/**
 * 
 */
package com.steria.BizTalk.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.AggregateIterable;
import com.steria.BizTalk.dao.TripViewDao;
import com.steria.BizTalk.dto.SiteInformation;

/**
 * @author ndevados
 *
 */
@Service
public class TripViewServices {

	@Autowired
	TripViewDao tripviewDao;

	private static final Logger logger = LoggerFactory.getLogger(TripViewServices.class);
	
	public Map<String, Object> processedViewTrip(String startDate, String endDate) throws ParseException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Set<String> routeIdSet = new HashSet<>();
		Set<String> vehicleSet = new HashSet<>();
		int suspendedVehicles = 0;
		logger.debug("Tripview counter started");
		AggregateIterable<Document> informationList = tripviewDao.ptocessedTripView(startDate, endDate);
		logger.debug("Tripview counter end");
		
		for(Document sitInfo : informationList){
			routeIdSet.add((String) ((Document) sitInfo.get("_id")).get("routeId"));
			vehicleSet.add((String) ((Document) sitInfo.get("_id")).get("vehicle"));
		}
		
		resultMap.put("total", routeIdSet.size());
		resultMap.put("unique", vehicleSet.size());
		resultMap.put("suspended", suspendedVehicles);
		logger.debug("Driver data fetch started");
		resultMap.put("driverTableData", tripviewDao.getDriverTripData(startDate, endDate));
		logger.debug("Driver data fetch end");

		return resultMap;
	}

}

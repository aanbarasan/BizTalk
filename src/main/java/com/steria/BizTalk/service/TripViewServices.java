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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Map<String, Integer> processedViewTrip(String startDate, String endDate) throws ParseException {

		Map<String, Integer> resultMap = new HashMap<>();
		Set<String> routeIdSet = new HashSet<>();
		Set<String> vehicleSet = new HashSet<>();
		int suspendedVehicles = 0;

		List<SiteInformation> informationList = tripviewDao.ptocessedTripView(startDate, endDate);
		
		for(SiteInformation sitInfo : informationList){
			routeIdSet.add(sitInfo.getRouteId());
			vehicleSet.add(sitInfo.getVehicle());
		}
		
		resultMap.put("total", routeIdSet.size());
		resultMap.put("unique", vehicleSet.size());
		resultMap.put("suspended", suspendedVehicles);

		return resultMap;
	}

}

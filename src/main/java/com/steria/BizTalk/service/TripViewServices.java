/**
 * 
 */
package com.steria.BizTalk.service;

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

	public Map<String, Integer> processedViewTrip(String startDate, String endDate) {

		Map<String, Integer> resultMap = new HashMap<>();
		Set<String> journeyIdSet = new HashSet<>();
		Set<String> vehicleSet = new HashSet<>();
		int suspendedVehicles = 0;

		List<SiteInformation> informationList = tripviewDao.ptocessedTripView(startDate, endDate);

		for (int i = 0; i < informationList.size(); i++) {
			journeyIdSet.add(informationList.get(i).getJourneyAlias());
			vehicleSet.add(informationList.get(i).getVehicle());
		}

		resultMap.put("total", journeyIdSet.size());
		resultMap.put("unique", vehicleSet.size());
		resultMap.put("suspended", suspendedVehicles);

		return resultMap;
	}

}

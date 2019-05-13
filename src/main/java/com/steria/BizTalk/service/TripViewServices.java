/**
 * 
 */
package com.steria.BizTalk.service;

import java.util.List;
import java.util.Map;

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

		Map<String, Integer> resultMap;
		int totalTripCount=0;
		int totalUniqVechicleCount=0;

		List<SiteInformation> informationList = tripviewDao.ptocessedTripView(startDate, endDate);
		
		//find total trip count
//		informationList.stream().distinct().(p-> p.)
		return null;

	}

}

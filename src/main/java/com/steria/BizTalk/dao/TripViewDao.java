/**
 * 
 */
package com.steria.BizTalk.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.steria.BizTalk.dto.SiteInformation;

/**
 * @author ndevados
 *
 */

@Repository
public class TripViewDao {

	@Autowired
	MongoTemplate mongoTemplate;

	public List<SiteInformation> ptocessedTripView(String startDate, String endDate) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		List<SiteInformation> siteList = mongoTemplate
				.find(Query.query(Criteria.where("journeyStart").gte(format.parse(startDate)).lte(format.parse(endDate))), SiteInformation.class);

		return siteList;
	}

}

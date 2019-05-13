/**
 * 
 */
package com.steria.BizTalk.dao;

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

	public List<SiteInformation> ptocessedTripView(String startDate, String endDate) {

		List<SiteInformation> siteList = mongoTemplate
				.find(Query.query(Criteria.where("journeyStart").gte(startDate).lte(endDate)), SiteInformation.class);

		return siteList;
	}

}

/**
 * 
 */
package com.steria.BizTalk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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
public class TrackingDAO {

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongo;

	public boolean addSiteInfo(List<SiteInformation> siteList, String tripId, String fileType) {

		boolean status = false;

		try {
			SiteInformation SiteInformation = mongoTemplate.findOne(Query.query(Criteria.where("routeId").is(tripId)),
					SiteInformation.class);
			if (SiteInformation == null) {

				mongo.insert(siteList, "TripDetails");
			}
			status = true;
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return status;

	}

}
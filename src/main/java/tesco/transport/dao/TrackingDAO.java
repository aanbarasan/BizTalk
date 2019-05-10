/**
 * 
 */
package tesco.transport.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import tesco.transport.dto.SiteInformation;

/**
 * @author ndevados
 *
 */
@Repository
public class TrackingDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	public boolean addSiteInfo(List<SiteInformation> siteList, String tripId) {

		boolean status = false;

		try {

			SiteInformation SiteInformation = mongoTemplate.findOne(Query.query(Criteria.where("routeId").is(tripId)),
					SiteInformation.class);
			if (SiteInformation == null) {
				mongoTemplate.insertAll(siteList);
			} else {
				System.out.println("Route Id already Exist");
			}

			status = true;
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return status;

	}

}

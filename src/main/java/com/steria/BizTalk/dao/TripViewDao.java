/**
 * 
 */
package com.steria.BizTalk.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
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
		List<SiteInformation> siteList = mongoTemplate.find(
				Query.query(Criteria.where("journeyStart").gte(format.parse(startDate)).lte(format.parse(endDate))),
				SiteInformation.class);

		return siteList;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getDriverTripData(String startDate, String endDate) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		AggregateIterable<Document> output = mongoTemplate.getCollection("siteInformation")
				.aggregate(Arrays.asList(Aggregates.match(Filters.gte("journeyStart", format.parse(startDate))),
						Aggregates.match(Filters.lte("journeyStart", format.parse(endDate))),
						Aggregates.group("$resourceDriver", Accumulators.push("journeyStart", "$journeyStart"),
								Accumulators.push("resourceDriver", "$resourceDriver"), Accumulators
										.push("journeyAlias", "$journeyAlias"))));

		MongoCursor<Document> i = output.iterator();

		List<HashMap<String, Object>> doc = new ArrayList<HashMap<String, Object>>();
		while (i.hasNext()) {
			Document d = i.next();
			HashMap<String, Object> map = new HashMap<>();
			ArrayList<String> jdatelist = (ArrayList<String>) d.get("journeyStart");
			ArrayList<String> journeyAliasList = (ArrayList<String>) d.get("journeyAlias");
			
			if(jdatelist != null){
				map.put("journeyStart", jdatelist.stream().distinct().toArray());
			}
			if(journeyAliasList != null){
				map.put("journeyAlias", journeyAliasList.stream().distinct().toArray());
			}
			map.put("name", d.get("_id"));
			doc.add(map);
		}

		return doc;
	}
}

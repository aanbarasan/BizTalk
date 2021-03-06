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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

/**
 * @author ndevados
 *
 */

@Repository
public class TripViewDao {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final Logger logger = LoggerFactory.getLogger(TripViewDao.class);

	public AggregateIterable<Document> ptocessedTripView(String startDate, String endDate, String type) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		AggregateIterable<Document> output = mongoTemplate.getCollection("TripDetails")
				.aggregate(Arrays.asList(Aggregates.match(Filters.eq("status", type)),
						Aggregates.match(Filters.gte("journeyStart", format.parse(startDate))),
						Aggregates.match(Filters.lte("journeyStart", format.parse(endDate))),
						Aggregates.group(new Document("routeId", "$routeId").append("vehicle", "$vehicle"))));
		return output;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getDriverTripData(String startDate, String endDate, String type) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		AggregateIterable<Document> output = mongoTemplate.getCollection("TripDetails")
				.aggregate(Arrays.asList(Aggregates.match(Filters.eq("status", type)),
						Aggregates.match(Filters.gte("journeyStart", format.parse(startDate))),
						Aggregates.match(Filters.lte("journeyStart", format.parse(endDate))),
						Aggregates.group(new Document("resourceDriver", "$resourceDriver").append("journeyStart", "$journeyStart"),
								Accumulators.push("journeyAlias", "$journeyAlias"))));

		MongoCursor<Document> i = output.iterator();
		logger.debug("Driver data looping started");
		List<HashMap<String, Object>> doc = new ArrayList<HashMap<String, Object>>();
		while (i.hasNext()) {
			Document d = i.next();
			HashMap<String, Object> map = new HashMap<>();
			ArrayList<String> jdatelist = (ArrayList<String>) d.get("journeyStart");
			ArrayList<String> journeyAliasList = (ArrayList<String>) d.get("journeyAlias");

			if (jdatelist != null) {
				map.put("journeyStart", jdatelist.stream().distinct().toArray());
			}
			if (journeyAliasList != null) {
				map.put("journeyAlias", journeyAliasList.stream().distinct().toArray());
			}
			map.put("name", ((Document) d.get("_id")).get("resourceDriver"));
			map.put("journeyStart", ((Document) d.get("_id")).get("journeyStart"));
			doc.add(map);
		}
		logger.debug("Driver data looping end");

		return doc;
	}
}

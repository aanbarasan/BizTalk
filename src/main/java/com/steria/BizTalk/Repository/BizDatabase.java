package com.steria.BizTalk.Repository;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class BizDatabase {

	static MongoDatabase db;

	static {
		
		MongoClient mongo = new MongoClient("localhost", 27017);
		db = mongo.getDatabase("biztalk");
	}

	public static void insertObj(String collectionName, Document document) {

		MongoCollection<Document> col = db.getCollection(collectionName);
		col.insertOne(document);
	}

}

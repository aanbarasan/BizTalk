/**
 * 
 */
package tesco.transport.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import tesco.transport.dto.Login;
import tesco.transport.dto.UserDetails;

/**
 * @author ndevados
 *
 */
@Repository
public class LoginDaoImpl {

	@Autowired
	MongoTemplate mongoTemplate;

	public Login getAuthDetails(String userName) {
		Login login = new Login();
		System.out.println(userName);
		login = mongoTemplate.findOne(Query.query(Criteria.where("username").is(userName)), Login.class);

		return login;

	}

	public boolean addUser(UserDetails userdetails) {

		boolean status = false;

		try {
			mongoTemplate.insert(userdetails);
			status = true;
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return status;

	}

	public List<UserDetails> viewlist() {

		List<UserDetails> list = new ArrayList<>();

		list = mongoTemplate.findAll(UserDetails.class);
		return list;

	}

}

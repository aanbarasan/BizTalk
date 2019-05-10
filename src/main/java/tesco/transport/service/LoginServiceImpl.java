/**
 * 
 */
package tesco.transport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tesco.transport.dao.LoginDaoImpl;
import tesco.transport.dto.Login;
import tesco.transport.dto.UserDetails;

/**
 * @author ndevados
 *
 */
@Service
public class LoginServiceImpl {

	@Autowired
	LoginDaoImpl loginDaoImpl;

	public String loginAuth(Login loginParam) {

		Login login = loginDaoImpl.getAuthDetails(loginParam.getUsername());

		String response = "failed";

		if (loginParam != null) {

			if (loginParam.getUsername().equalsIgnoreCase(login.getUsername())
					&& loginParam.getPassword().equalsIgnoreCase(login.getPassword())) {

				response = "view";
			}
		}
		return response;

	}

	public String addUser(UserDetails userdetails) {

		boolean status = loginDaoImpl.addUser(userdetails);
		String response = "failed";

		if (status) {
			response = "success";
		}

		return response;

	}

	public List<UserDetails> viewUserList() {

		return loginDaoImpl.viewlist();

	}

}

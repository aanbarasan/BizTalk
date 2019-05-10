package tesco.transport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tesco.transport.dto.Login;
import tesco.transport.dto.UserDetails;
import tesco.transport.service.LoginServiceImpl;

@Controller
public class IndexController {

	@Autowired
	LoginServiceImpl loginServiceImpl;

	@RequestMapping("/")
	private String redirectIndex() {
		System.out.println("index");
		return "home";

	}

	@PostMapping("/login")
	private String redirectTOLogin(@ModelAttribute("Login") Login login) {
		System.out.println("Login");
		return loginServiceImpl.loginAuth(login);

	}

	@PostMapping("/add")
	private String addRecord(@ModelAttribute("UserDetails") UserDetails userdetails) {
		return loginServiceImpl.addUser(userdetails);

	}

	@PostMapping("/update")
	private String updateRecord(@ModelAttribute("UserDetails") UserDetails userdetails) {
		return null;

	}

	@RequestMapping("/addredirect")
	private String addRedirect() {

		return "index";

	}

	@RequestMapping("/view")
	private ModelAndView view() {
		ModelAndView modelAndView = new ModelAndView();
		List<UserDetails> userlist = loginServiceImpl.viewUserList();

		modelAndView.addObject("userlist", userlist);
		modelAndView.setViewName("view");
		return modelAndView;

	}

}

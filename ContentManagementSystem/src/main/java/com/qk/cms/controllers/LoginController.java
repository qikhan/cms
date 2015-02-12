package com.qk.cms.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qk.cms.vo.LoginVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("login") LoginVo loginVo,
			HttpSession session) {

		logger.info("Login authentication");

		ModelAndView modelAndView = new ModelAndView("home", "login",
				new LoginVo());
		System.out.println(session.getId());
		Map<String, Object> model = modelAndView.getModel();
		if (loginVo.getUserName().isEmpty() == false
				&& loginVo.getPassword().isEmpty() == false) {
			model.put("userName", loginVo.getUserName());
			model.put("authValid", true);
		} else {
			model.put("loginMessage", "Invalid login information.");
			model.put("loginStyle", "btn-danger");
			model.put("login", loginVo);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {

		logger.info("Login authentication");

		ModelAndView modelAndView = new ModelAndView("home", "login",
				new LoginVo());

		Map<String, Object> model = modelAndView.getModel();
		model.put("userName", null);
		model.put("authValid", false);
		model.put("loginStyle", "btn-success");
		return modelAndView;
	}
}

package com.qk.cms.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RegisterController {

	private static final Logger logger = LoggerFactory
			.getLogger(RegisterController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		logger.info("CMS Registration page.");

		ModelAndView modelAndView = new ModelAndView("register");

		return modelAndView;
	}

}

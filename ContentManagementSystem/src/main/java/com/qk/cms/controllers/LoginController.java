package com.qk.cms.controllers;

import java.util.Map;

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
	public ModelAndView login(@ModelAttribute("login") LoginVo loginVo) {

		logger.info("Login authentication");

		ModelAndView modelAndView = new ModelAndView("home", "login", loginVo);

		Map<String, Object> model = modelAndView.getModel();
		model.put("serverTime", "Name: " + loginVo.getUserName());
		model.put("authValid", true);

		return modelAndView;
	}
}

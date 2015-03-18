package com.qk.cms.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qk.cms.entity.CmsUser;
import com.qk.cms.view.RegistrationStatus;

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
	public ModelAndView initPage(HttpSession session) {
		logger.info("CMS Registration page.");

		ModelAndView modelAndView = new ModelAndView("register", "cmsUser",
				new CmsUser());
		modelAndView.getModelMap().put("status", RegistrationStatus.INPUT);
		return modelAndView;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpSession session,
			@ModelAttribute("cmsUser") CmsUser cmsUser) {

		logger.info("CMS Registration processing.");

		ModelAndView modelAndView = new ModelAndView("register", "cmsUser",
				new CmsUser());
		modelAndView.getModelMap().put("status", RegistrationStatus.SUCCESS);

		return modelAndView;
	}
}

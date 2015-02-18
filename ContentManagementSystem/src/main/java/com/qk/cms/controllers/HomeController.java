package com.qk.cms.controllers;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qk.cms.auth.SessionNotFoundException;
import com.qk.cms.auth.UserSession;
import com.qk.cms.auth.UserSessionManager;
import com.qk.cms.vo.LoginVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		logger.info("Welcome home! The client locale is {}.");

		LoginVo loginVo = new LoginVo();
		ModelAndView modelAndView = new ModelAndView("home", "login", loginVo);
		Map<String, Object> model = modelAndView.getModel();

		try {
			UserSession userSession = UserSessionManager.getInstance()
					.getUserSession(session.getId());
			loginVo = userSession.getLoginVo();
			loginVo.updateModel(model);

		} catch (ExecutionException e) {
			//
		} catch (SessionNotFoundException e) {
			//
			logger.info("Session info available");
		}

		model.put("loginStyle", "btn-success");
		return modelAndView;
	}

}

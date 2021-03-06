package com.qk.cms.controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qk.cms.auth.SessionNotFoundException;
import com.qk.cms.auth.UserSessionManager;
import com.qk.cms.entity.CmsUser;
import com.qk.cms.service.OLTPServiceManager;
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
	 * 
	 * @throws SessionNotFoundException
	 * @throws ExecutionException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("login") LoginVo loginVo,
			HttpSession session) throws ExecutionException,
			SessionNotFoundException {

		logger.info("Login authentication");

		ModelAndView modelAndView = new ModelAndView("home", "login",
				new LoginVo());
		Map<String, Object> model = modelAndView.getModel();
		if (loginVo.getUserName().isEmpty() == false
				&& loginVo.getPassword().isEmpty() == false
				&& performAuthentication(loginVo)) {

			loginVo.setAuthValid(true);
			UserSessionManager.getInstance().addUserSession(session.getId(),
					loginVo);

		} else {
			model.put("loginMessage", "Invalid login information.");
			model.put("loginStyle", "btn-danger");
		}
		loginVo.updateModel(model);

		return modelAndView;
	}

	private boolean performAuthentication(LoginVo loginVo) {
		CmsUser user = OLTPServiceManager.getInstance().getUser(
				loginVo.getUserName());
		if (user != null && user.getPassword().equals(loginVo.getPassword())) {
			return true;
		}

		return false;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {

		logger.info("Login authentication");

		ModelAndView modelAndView = new ModelAndView("home", "login",
				new LoginVo());
		UserSessionManager.getInstance().invalidateUserSession(session.getId());
		Map<String, Object> model = modelAndView.getModel();
		model.put("userName", null);
		model.put("authValid", false);
		model.put("loginStyle", "btn-success");
		return modelAndView;
	}
}

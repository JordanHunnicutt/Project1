package com.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.web.models.User;

public class SessionController {

	public void setSessionUser(HttpServletRequest req, User u) {
		HttpSession session = req.getSession();
		session.setAttribute("CurrentUser", u);
	}
	
	public User getSessionUser(HttpServletRequest req) {
		return (User)req.getSession().getAttribute("CurrentUser");
	}
	
	public void invalidate(HttpServletRequest req) {
		req.getSession().invalidate();
	}
	
}

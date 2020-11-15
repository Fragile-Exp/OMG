package com.omg.cmn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 컨트롤러 호출 전 Session 유무 Check
	 * session이 없으면 Login Page 이동
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOG.debug(" >>>> Interceptor PreHandler <<<<");
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute("employee");
	
		// 로그인 객체가 null이면 로그인 페이지로 이동.
		String url = request.getRequestURI();
		if(null == obj) {
			response.sendRedirect(request.getContextPath()+"/employee/login.do");
			LOG.debug("비로그인 상태 : " + url);
			return false;
		} 
		LOG.debug("로그인 상태 : " + url);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOG.debug(" >>>> Interceptor PostHandler <<<<");
		super.postHandle(request, response, handler, modelAndView);
	}
}

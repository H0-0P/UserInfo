package com.ctgu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ctgu.pojo.User;

public class MyInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
//		System.out.println("请求结束");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
//		System.out.println("正在请求处理");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//		System.out.println("请求正在检测");
		//判定用户是否登录，如果登录，正常跳转
		//否则重定向到登陆页面或者注册
		User u = (User) request.getSession().getAttribute("cur_user");
		if(u == null){
			response.sendRedirect(request.getContextPath()+"/user/loginFirst.do");
			return false;
		}
		return true;
	}
	

}

package com.ctgu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.interfaces.PBEKey;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ctgu.dao.PictureDao;
import com.ctgu.dao.UserDao;
import com.ctgu.pojo.Picture;
import com.ctgu.pojo.User;
import com.ctgu.utils.PageBean;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/user")
public class UserController {
	//ע��ӿڶ���    Ĭ��setע��
	@Resource
	private UserDao userDao;
	@Resource
	private PictureDao picDao;
	@Resource
	private PageBean<User> pageBean;
	
	@RequestMapping("/loginFirst.do")
	public String loginFirst(){
		return "/interceptor_loginFirst";
	}
	
	@RequestMapping("/toRegist.do")
	public String regist(){
		return "/regist";
	}
	
	@RequestMapping("/findByName.do")
	public @ResponseBody ModelAndView findByName(@RequestParam("username")String username){
		Map<String, String> map = new HashMap<String, String>();
		User u = null;
		try {
			u = userDao.findByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�ж��û����Ƿ����
		if(u == null){
			map.put("msg", "success");
		} else{
			map.put("msg", "error");
		}
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}
	
	//���ע��submit��ť
	@RequestMapping("/regist.do")
	public String register(User user){
		userDao.register(user);
		return "/regist_success";
	}
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		//�����쳣
		//Integer.parseInt("qaq");
//		User u = null;
//		System.out.println(u.getGender());
		return "/login";
	}
	
	//�����¼submit��ť��ʱ��
	@RequestMapping("/login.do")
	public @ResponseBody ModelAndView login(@RequestParam("username")String username, @RequestParam("pwd")String password, HttpServletRequest request) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> user_map = new HashMap<String, String>();
		List<User> list = null;
		user_map.put("username", username);
		user_map.put("password", password);
		User user = userDao.login(user_map);
		if(user == null){
			map.put("msg", "error");
		}else{
			//���õ�ǰҳΪ��ҳ
			pageBean.setCurrentPage(1);
			//�����ܼ�¼
			pageBean.setTotalCount(userDao.getTotalCount());
			//PageHelper.startPage(pageNum, pageSize)
			list = userDao.findAll((pageBean.getCurrentPage()-1)*pageBean.getPageCount(), pageBean.getPageCount());
			//ÿҳ��������
			pageBean.setPageData(list);
			request.getSession().setAttribute("pageBean", pageBean);
//			request.getSession().setAttribute("list", list);
			request.getSession().setAttribute("cur_user", user);
			map.put("msg", "success");
		}
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}
	
	@RequestMapping("/switchPage.do")
	public String switchPage(@RequestParam("currentPage")Integer currentPage, HttpServletRequest request){
		pageBean = (PageBean<User>) request.getSession().getAttribute("pageBean");
		
		if(currentPage <= 0){
			pageBean.setCurrentPage(pageBean.getTotalPage());
		}else if(currentPage > pageBean.getTotalPage()){
			pageBean.setCurrentPage(1);
		}else{
			pageBean.setCurrentPage(currentPage);
		}
		
		pageBean.setPageData(userDao.findAll((pageBean.getCurrentPage()-1)*pageBean.getPageCount(), pageBean.getPageCount()));
		request.getSession().setAttribute("pageBean", pageBean);
		return "redirect:/user/userList.do";
	}
	
	@RequestMapping("/userList.do")
	public String userList(){
		return "/userList";
	}
	
	@RequestMapping("/userDetail.do")
	public ModelAndView upLoad(@RequestParam("id")Integer id, HttpServletRequest request){
		User u = userDao.findById2(id);
		//List<Picture> pList = picDao.findByUserId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vis_user", u);
		//ʹ��Ƕ�ײ�ѯ֮��
		map.put("pList", u.getPicList());
		return new ModelAndView("/userDetail", map);
	}
	
	//ɾ���û�
	@RequestMapping("/delete.do")
	public String deleteUser(@RequestParam("id")Integer id, HttpServletRequest request){
		userDao.deleteUser(id);
		return "/delete_success";
	}
	
	//��ѯ
	@RequestMapping("/query.do")
	public ModelAndView queryUser(){
		//�������޸�
//		User cur_user = (User) request.getSession().getAttribute("cur_user");
//		User user = userDao.findById(cur_user.getId());
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("update_user", user);
		return new ModelAndView("/updateUser");
	}
	
	//����
	@RequestMapping("/update.do")
	public ModelAndView updateUser(User user, HttpServletRequest request){
		request.getSession().setAttribute("cur_user", user);
		userDao.updateUser(user);
		PageBean<User> pageBean = (PageBean<User>) request.getSession().getAttribute("pageBean");
		List<User> list = userDao.findAll((pageBean.getCurrentPage()-1)*pageBean.getPageCount(), pageBean.getPageCount());
		pageBean.setPageData(list);
		return new ModelAndView("/userList");
	}
	
	//�˳���¼
	@RequestMapping("/loginOut.do")
	public String loginOut(HttpServletRequest request){
		request.getSession().removeAttribute("cur_user");
		return "/login";
	}
	
	//ע�ⷽʽ�Զ����쳣����
	@ExceptionHandler
	public String myException(Exception e, HttpServletRequest request){
		if(e instanceof NumberFormatException){
			request.setAttribute("message", "��ʽ����ȷ");
		}else if(e instanceof NullPointerException){
			request.setAttribute("message", "��ָ��");
		}
		return "/error";
	}
	
}

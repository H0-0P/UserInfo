package com.ctgu.dao;

import java.util.List;
import java.util.Map;

import com.ctgu.annotation.MybatisAnnotation;
import com.ctgu.pojo.Picture;
import com.ctgu.pojo.User;

@MybatisAnnotation
public interface UserDao {
	public User findByName(String username);
	public User findById(int id);
	public User findById2(int id);
	//插入数据
	public void register(User user);
	//登录
	public User login(Map<String, String> map);
	//查询所有数据
	public List<User> findAll(Integer pStart, Integer pSize);
	//查询总记录
	public int getTotalCount();
	//删除用户
	public void deleteUser(Integer id);
	//修改用户
	public void updateUser(User user);
	
	//关联映射
	public List<Picture> findPicByUserId(Integer userId);
}

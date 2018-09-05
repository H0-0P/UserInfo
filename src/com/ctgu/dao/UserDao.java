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
	//��������
	public void register(User user);
	//��¼
	public User login(Map<String, String> map);
	//��ѯ��������
	public List<User> findAll(Integer pStart, Integer pSize);
	//��ѯ�ܼ�¼
	public int getTotalCount();
	//ɾ���û�
	public void deleteUser(Integer id);
	//�޸��û�
	public void updateUser(User user);
	
	//����ӳ��
	public List<Picture> findPicByUserId(Integer userId);
}

package com.ctgu.dao;

import java.util.List;

import com.ctgu.annotation.MybatisAnnotation;
import com.ctgu.pojo.Picture;

@MybatisAnnotation
public interface PictureDao {
	//����
	public void save(Picture p);
	//�����û�id��ѯͼƬ����
	public List<Picture> findByUserId(Integer userId);
}

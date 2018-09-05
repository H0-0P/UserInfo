package com.ctgu.dao;

import java.util.List;

import com.ctgu.annotation.MybatisAnnotation;
import com.ctgu.pojo.Picture;

@MybatisAnnotation
public interface PictureDao {
	//保存
	public void save(Picture p);
	//根据用户id查询图片名称
	public List<Picture> findByUserId(Integer userId);
}

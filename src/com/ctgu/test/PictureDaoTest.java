package com.ctgu.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ctgu.dao.PictureDao;
import com.ctgu.pojo.Picture;

public class PictureDaoTest {
	
	@Test
	public void test_save(){
		//获得容器对象
		ApplicationContext context = new ClassPathXmlApplicationContext("com/ctgu/source/applicationContext.xml");
		//通过容器实例化sqlSessionFactory
		DefaultSqlSessionFactory factory = context.getBean("sqlSessionFactory", DefaultSqlSessionFactory.class);
		SqlSession session = factory.openSession();
		
		PictureDao dao = session.getMapper(PictureDao.class);
		Picture p = new Picture();
		p.setPicName("123456");
		p.setUserId(15);
		p.setId(1);
		dao.save(p);
		session.commit();
		session.close();
	}
}

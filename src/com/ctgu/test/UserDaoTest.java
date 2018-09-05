package com.ctgu.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ctgu.dao.UserDao;
import com.ctgu.pojo.User;

public class UserDaoTest {

	@Test
	public void testFindById() throws Exception {
		//获得容器对象
		ApplicationContext context = new ClassPathXmlApplicationContext("com/ctgu/source/applicationContext.xml");
		//通过容器实例化sqlSessionFactory
		DefaultSqlSessionFactory factory = context.getBean("sqlSessionFactory", DefaultSqlSessionFactory.class);
		SqlSession session = factory.openSession();
		
		UserDao dao = session.getMapper(UserDao.class);
		User user = dao.findById2(15);
		System.out.println(user);
		
		session.close();
	}
	
	@Test
	public void testFindByName() throws Exception {
		//获得容器对象
		ApplicationContext context = new ClassPathXmlApplicationContext("com/ctgu/source/applicationContext.xml");
		//通过容器实例化sqlSessionFactory
		DefaultSqlSessionFactory factory = context.getBean("sqlSessionFactory", DefaultSqlSessionFactory.class);
		SqlSession session = factory.openSession();
		
		UserDao dao = session.getMapper(UserDao.class);
		User user = dao.findByName("H0_0P");
		System.out.println(user);
		
		session.close();
	}
}

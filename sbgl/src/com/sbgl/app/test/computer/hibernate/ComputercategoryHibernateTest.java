package com.sbgl.app.test.computer.hibernate;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sbgl.app.entity.Computercategory;



public class ComputercategoryHibernateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 读取配置文件hibernate.cfg.xml
		Configuration cfg = new Configuration().configure(new File("file/hibernate.cfg.xml"));
		
		// 创建SessionFactory
		SessionFactory factory = cfg.buildSessionFactory();
		
		// 创建Session
		Session session = factory.openSession();
		
		// 使用HQL查询
		String hql = "FROM Computercategory";
		
		// 通过Query方法查询
		Query q = session.createQuery(hql);

		
		//查询结果保存到list中
		List<Computercategory> list = q.list();
		
		//遍历是否存在该id的产品，如果存在则进行输出
		Iterator<Computercategory> iter = list.iterator();
		while(iter.hasNext()) {		
			Computercategory obj = iter.next();
		//	System.out.println(obj.getName());
		}
		
		// 关闭session
		if(session.isOpen()) {
			session.close();
		}
	}

}

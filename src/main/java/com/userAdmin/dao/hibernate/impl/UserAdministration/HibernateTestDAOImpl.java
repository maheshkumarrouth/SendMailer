package com.userAdmin.dao.hibernate.impl.UserAdministration;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userAdmin.dao.hibernate.interfaces.UserAdministration.TestDAO;
import com.userAdmin.dao.model.UserAdministration.UserDetails;
import com.userAdmin.dao.model.UserAdministration.VerificationToken;

@Repository
public class HibernateTestDAOImpl implements TestDAO{
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	public void save(UserDetails userDetails) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.save(userDetails);
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	

	@Override
	public void save(VerificationToken ver) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.save(ver);
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	
}

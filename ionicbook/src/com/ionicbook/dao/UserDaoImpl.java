package com.ionicbook.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ionicbook.entity.User;
import com.ionicbook.util.HibernateSessionFactory;

public class UserDaoImpl implements UserDao {

	private Transaction transaction;
	
	@Override
	public boolean login(String userAccount, String userPassword) {
		if(userAccount != null && userPassword != null) {
			Session session = HibernateSessionFactory.getSession();
			String hql = "from User as a where a.userAccount='" + userAccount + "' and a.userPassword='" + userPassword +"'";
			Query query = (Query)session.createQuery(hql);
			User user = (User)query.uniqueResult();
			if(user != null) {
				HibernateSessionFactory.closeSession();
				return true;
			}
			HibernateSessionFactory.closeSession();
		}
		return false;
	}

	@Override
	public void saveUser(User user) {
		Session session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public User getUser(int userId) {
		Session session = HibernateSessionFactory.getSession();
		User user = (User)session.load(User.class, userId);
		return user;
	}

	@Override
	public int getUserIdByUserAccount(String userAccount) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from User as a where a.userAccount='" + userAccount + "'";
		Query query = (Query)session.createQuery(hql);
		User user = (User)query.uniqueResult();
		if(user != null) {
			HibernateSessionFactory.closeSession();
			return user.getUserId();
		}
		HibernateSessionFactory.closeSession();
		return user.getUserId();
	}

}

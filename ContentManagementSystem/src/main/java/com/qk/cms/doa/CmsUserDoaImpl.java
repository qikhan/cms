package com.qk.cms.doa;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.qk.cms.entity.CmsUser;

public class CmsUserDoaImpl implements CmsUserDoa {

	private SessionFactory sessionFactory_;

	public void setSessionFactory(SessionFactory sessionFactory) {
		sessionFactory_ = sessionFactory;
	}

	@Override
	public void save(CmsUser user) {
		Session session = sessionFactory_.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmsUser> list() {
		Session session = sessionFactory_.openSession();
		List<CmsUser> userList = session.createQuery("from user").list();
		session.close();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CmsUser findByUserName(String username) {

		List<CmsUser> users = new ArrayList<CmsUser>();

		users = sessionFactory_.getCurrentSession()
				.createQuery("from User where user_name=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
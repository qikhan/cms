package com.qk.cms.doa;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qk.cms.entity.CmsUser;

public class CmsUserDoaImpl implements CmsUserDoa {

	private SessionFactory sessionFactory_;
	private static final Logger logger = LoggerFactory
			.getLogger(CmsUserDoaImpl.class);

	public void setSessionFactory(SessionFactory sessionFactory) {
		sessionFactory_ = sessionFactory;
	}

	@Override
	public boolean save(CmsUser user) {
		Session session = null;
		try {
			session = sessionFactory_.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(user);
			tx.commit();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmsUser> list() {
		Session session = sessionFactory_.openSession();
		List<CmsUser> userList = session.createQuery("from CmsUser").list();
		session.close();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CmsUser findByUserName(String username) {

		List<CmsUser> users = new ArrayList<CmsUser>();

		Session session = sessionFactory_.openSession();
		Query query = session.createQuery(
				"SELECT userName, password FROM CmsUser WHERE userName=?")
				.setParameter(0, username);
		users = query.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
package edu.npu.cs595.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.npu.cs595.dao.ActivityDao;
import edu.npu.cs595.domain.Activity;

@Repository("ActivityDaoHibernate")
@Transactional
public class ActivityDaoHibernateImpl implements ActivityDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Activity storeActivity(Activity activity) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(activity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return activity;
	}

	@Override
	public Activity findActivity(int activityId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Activity activity = null;
		try {
			tx = session.beginTransaction();
			activity = (Activity) session.get(Activity.class, activityId);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return activity;
	}

	@Override
	public void removeActivity(Activity activity) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(activity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findAllActivities() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Activity> result = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Activity");
			result = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public void removeAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("delete from Activity");
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void storeActivityList(List<Activity> list) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Activity a : list) {
				session.saveOrUpdate(a);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

}

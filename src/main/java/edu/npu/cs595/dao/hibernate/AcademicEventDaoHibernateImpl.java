package edu.npu.cs595.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs595.dao.AcademicEventDao;
import edu.npu.cs595.domain.AcademicEvent;

@Repository("AcademicEventDaoHibernate")
@Transactional
public class AcademicEventDaoHibernateImpl implements AcademicEventDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void storeEventList(List<AcademicEvent> list) {
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (AcademicEvent e : list) {
				session.saveOrUpdate(e);
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

	@Override
	public AcademicEvent storeEvent(AcademicEvent event) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(event);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return event;
	}

	@Override
	public AcademicEvent findEvent(int eventId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		AcademicEvent event = null;
		try {
			tx = session.beginTransaction();
			event = (AcademicEvent) session.get(AcademicEvent.class, eventId);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return event;
	}

	@Override
	public void removeEvent(AcademicEvent event) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(event);
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
	public List<AcademicEvent> findAllEvents() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<AcademicEvent> result = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from AcademicEvent");
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
			Query query = session.createQuery("delete from AcademicEvent");
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
}

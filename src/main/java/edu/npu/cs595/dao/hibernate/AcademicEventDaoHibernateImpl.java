package edu.npu.cs595.dao.hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	public AcademicEvent storeEvent(AcademicEvent event) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(event);
		return event;
	}

	@Override
	public AcademicEvent findEvent(int eventId) {
		Session session = sessionFactory.getCurrentSession();
		AcademicEvent event=(AcademicEvent)session.get(AcademicEvent.class, eventId);
		return event;
	}

	@Override
	public void removeEvent(AcademicEvent event) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(event);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AcademicEvent> findAllEvents() {
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from AcademicEvent");
		return query.list();
	}

}

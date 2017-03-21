package edu.npu.cs595.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs595.dao.NewsDao;
import edu.npu.cs595.domain.News;

@Repository("NewsDaoHibernate")
@Transactional
public class NewsDaoHibernateImpl implements NewsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void storeNewsList(List<News> list) {
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (News n : list) {
				session.saveOrUpdate(n);
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
	public News storeNews(News news) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(news);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return news;
	}

	@Override
	public News findNews(int newsId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		News news = null;
		try {
			tx = session.beginTransaction();
			news = (News) session.get(News.class, newsId);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return news;
	}

	@Override
	public void removeNews(News news) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(news);
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
	public List<News> findAllNews() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<News> result = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from News");
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
			Query query = session.createQuery("delete from News");
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
	public News findLatest() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		News result = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from News");
			query.setMaxResults(1);
			result = (News) query.list().get(0);
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

}

package edu.npu.cs595.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.npu.cs595.dao.NewsDao;
import edu.npu.cs595.domain.News;

public class NewsDaoHibernateImpl implements NewsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public News storeNews(News news) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(news);
		return news;
	}

	@Override
	public News findNews(int newsId) {
		Session session = sessionFactory.getCurrentSession();
		News news = (News) session.get(News.class, newsId);
		return news;
	}

	@Override
	public void removeNews(News news) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(news);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> findAllNews() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from news");
		return query.list();
	}

}

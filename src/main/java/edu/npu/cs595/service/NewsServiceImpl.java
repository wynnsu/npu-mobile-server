package edu.npu.cs595.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.cs595.dao.NewsDao;
import edu.npu.cs595.domain.News;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	@Qualifier("NewsDaoHibernate")
	private NewsDao newsDao;

	@Override
	public News getNewsById(int newsId) {
		return newsDao.findNews(newsId);
	}

	@Override
	public List<News> getNews() {
		return newsDao.findAllNews();
	}

}

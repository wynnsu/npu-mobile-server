package edu.npu.cs595.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.npu.cs595.crawler.Crawler;
import edu.npu.cs595.dao.NewsDao;
import edu.npu.cs595.domain.News;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	@Qualifier("NewsDaoHibernate")
	private NewsDao newsDao;

	@Autowired
	@Qualifier("NewsCrawler")
	private Crawler<News> newsCrawler;

	protected static Logger logger = Logger.getLogger(NewsService.class);

	@Override
	public News getNewsById(int newsId) {
		return newsDao.findNews(newsId);
	}

	@Override
	public List<News> getNews() {
		return newsDao.findAllNews();
	}

	// Fire at 7:00 AM everyday
	@Scheduled(cron = "0 0 7 * * ?")
	// @Scheduled(cron="15 * * * * ?")
	@Override
	public void updateNewsList() {
		try {
			logger.info("Retrieving data");
			List<News> list = newsCrawler.crawl();
			List<News> origin = newsDao.findAllNews();
			if (!origin.isEmpty()) {
				for (News n : origin) {
					newsDao.removeNews(n);
				}
			}
			for (News n : list) {
				newsDao.storeNews(n);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
}

package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.News;

public interface NewsDao {
	public News storeNews(News news);

	public News findNews(int newsId);

	public void removeNews(News news);

	public List<News> findAllNews();
	
	public void removeAll();
	
	public void storeNewsList(List<News> list);
}

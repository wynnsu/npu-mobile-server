package edu.npu.cs595.service;

import java.util.List;

import edu.npu.cs595.domain.News;

public interface NewsService {
	public News getNewsById(int newsId);

	public List<News> getNews();
}

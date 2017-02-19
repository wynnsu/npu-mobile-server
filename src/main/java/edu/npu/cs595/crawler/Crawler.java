package edu.npu.cs595.crawler;

import java.util.List;

public interface Crawler<E> {
	public List<E> crawl() throws Exception;
}

package edu.npu.cs595.crawler;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public interface Crawler<E> {
	static final String BASE_URL = "https://www.npu.edu/";

	public List<E> crawl() throws Exception;

	static Document getDoc(String url) throws IOException {
		return Jsoup.connect(BASE_URL + url).get();
	}
}

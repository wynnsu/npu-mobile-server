package edu.npu.cs595.crawler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.npu.cs595.domain.News;

@Component
@Qualifier("NewsCrawler")
public class NewsCrawlerImpl implements Crawler<News> {
	private final String news_url = "/news/campus-news";

	private List<News> getNews(Document pageDoc) throws ParseException {
		ArrayList<News> result = new ArrayList<>();
		Elements articleList = pageDoc.select("article");
		for (Element article : articleList) {
			Element header = article.select("header").first();
			Elements fieldList = article.getElementsByClass("field-item even");
			Element first = fieldList.first();
			Element last = fieldList.last();
			String title = header.text();
			String imgUrl = first.select("img").first().attr("src");
			String content = last.text();
			result.add(new News(imgUrl, title, content));
		}
		return result;
	}

	@Override
	public List<News> crawl() throws Exception {
		Document doc = Crawler.getDoc(news_url);
		Elements pages = doc.getElementsByClass("pager-item");
		List<News> result = new ArrayList<>();
		result.addAll(getNews(doc));
		for (Element page : pages) {
			doc = Crawler.getDoc(page.getElementsByTag("a").attr("href"));
			result.addAll(getNews(doc));
		}
		return result;
	}
}

package crawler;

import java.util.List;

import edu.npu.cs595.crawler.CourseCrawlerImpl;
import edu.npu.cs595.domain.Course;

public class TestClient {

	private static CourseCrawlerImpl courseCrawler = new CourseCrawlerImpl();

	public static void main(String[] args) {
		try {
			List<Course> result = courseCrawler.crawl();
			System.out.println(result.get(0).getTitle());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}

}

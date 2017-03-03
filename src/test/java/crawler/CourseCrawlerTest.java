package crawler;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import edu.npu.cs595.crawler.Crawler;
import edu.npu.cs595.domain.Course;

public class CourseCrawlerTest {

	@Autowired
	@Qualifier("CourseCrawler")
	Crawler<Course> courseCrawler;

	@Test
	public void test() {
		try {
			List<Course> result = courseCrawler.crawl();
			System.out.println(result.get(0).getTitle());
			assert (result.get(0).getId() == 123);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

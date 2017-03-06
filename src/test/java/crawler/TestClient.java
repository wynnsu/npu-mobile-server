package crawler;

import java.util.List;

import edu.npu.cs595.crawler.CourseCrawlerImpl;
import edu.npu.cs595.domain.Course;
import edu.npu.cs595.domain.Credential;

public class TestClient {

	private static CourseCrawlerImpl courseCrawler = new CourseCrawlerImpl();

	public static void main(String[] args) {
		try {
			Credential cred = new Credential();
			cred.setId("15325sy");
			cred.setBase64Password("NGQ1ZDI4MDIyZm5wdQ==");
			List<Course> result = courseCrawler.crawl(cred);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

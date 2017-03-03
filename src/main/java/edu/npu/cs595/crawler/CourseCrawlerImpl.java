package edu.npu.cs595.crawler;

import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.impl.dv.util.Base64;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.npu.cs595.domain.Course;

@Component
@Qualifier("CourseCrawler")
public class CourseCrawlerImpl implements Crawler<Course> {

	@Autowired
	Authenticator auth;
	private final String course_url = "";

	private List<Course> getCourses(Document pageDoc) {
		ArrayList<Course> result = new ArrayList<>();
		System.out.println(pageDoc.html());
		Course course=new Course();
		course.setId(123);
		course.setTitle(pageDoc.html());
		result.add(course);
		return result;
	}

	@Override
	public List<Course> crawl() throws Exception {
		final String credential = "15325sy:4d5d28022fnpu";
		Document doc = Crawler.getDoc(course_url, Base64.encode(credential.getBytes()));
		return getCourses(doc);
	}

}

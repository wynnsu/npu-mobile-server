package edu.npu.cs595.crawler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.npu.cs595.domain.Course;

@Component
@Qualifier("CourseCrawler")
public class CourseCrawlerImpl extends Crawler<Course> implements Parser<Course> {
	private final String course_url = "/academics/class-schedule";

	protected static Logger logger = Logger.getLogger(CourseCrawlerImpl.class);

	@Override
	public List<Course> crawl() throws Exception {
		Document doc = Crawler.getRelativeDoc(course_url);
		List<Course> result = new ArrayList<>();
		result.addAll(parseDocument(doc));
		return result;
	}

	@Override
	public List<Course> parseDocument(Document doc) throws ParseException {
		List<Course> result = new ArrayList<>();
		Elements links = doc.getElementsByTag("a");
		for (Element l : links) {
			if (l.text().equals("Class Schedule")) {
				String schedule = l.attr("href");
				try {
					Document classDoc = Crawler.getDoc(schedule);
					Elements tables = classDoc.getElementsByTag("table");
					for (Element t : tables) {
						if (t.attr("class").equals("Border")) {
							Elements rows = t.select("tr");
							for (Element r : rows) {
								Elements cols = r.select("td");
								if (cols.size() <= 0)
									continue;
								String head = cols.get(0).text();
								if (cols.size() < 10 || head.equals("#"))
									continue;
								Course course = new Course();
								course.setId(cols.get(1).text());
								course.setTitle(cols.get(3).text());
								course.setCredits(Double.parseDouble(cols.get(4).text()));
								course.setPrerequisite(cols.get(5).text());
								course.setInstructor(cols.get(6).text());
								course.setTime(cols.get(8).text());
								course.setClassroom(cols.get(9).text());
								result.add(course);
							}
						}

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}

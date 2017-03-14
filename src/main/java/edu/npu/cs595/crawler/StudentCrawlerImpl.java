package edu.npu.cs595.crawler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.npu.cs595.domain.Student;
import edu.npu.cs595.resthandler.NPUMobileRestHandler;

@Component
@Qualifier("StudentCrawler")
public class StudentCrawlerImpl extends Crawler<Student> implements Parser<Student> {

	// private final String student_url = "";
	private String studentId;
	private String base64Password;
	private Logger logger = Logger.getLogger(StudentCrawlerImpl.class);

	@Override
	public List<Student> crawl(String studentId, String base64Password) throws Exception {
		this.studentId = studentId;
		this.base64Password = base64Password;
		Document doc = Crawler.getStudentDoc("", studentId, base64Password);
		List<Student> result = new ArrayList<>();
		if (doc == null) {
			return result;
		}
		result.addAll(parseDocument(doc));
		return result;
	}

	@Override
	public List<Student> parseDocument(Document doc) throws ParseException {
		Student student = new Student();
		student.setId(studentId);
		student.setBase64Password(base64Password);
		logger.info(doc.select(".stuInfo").text());
		Element info = doc.select(".stuInfo").get(0);
		Elements lines = info.getElementsByClass("stuInfo-display-field");
		logger.info(lines.text());
		student.setName(lines.get(0).text());
		student.setEmail(lines.get(2).text());
		student.setAddress(lines.get(3).text());
		student.setProgram(lines.get(4).text());

		List<Student> result = new ArrayList<>();
		result.add(student);
		return result;
	}

}

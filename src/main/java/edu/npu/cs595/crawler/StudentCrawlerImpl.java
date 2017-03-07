package edu.npu.cs595.crawler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.npu.cs595.domain.Student;

@Component
@Qualifier("StudentCrawler")
public class StudentCrawlerImpl extends Crawler implements Parser<Student> {

	private final String student_url = "";
	private String studentId;
	private String base64Password;

	@Override
	public List<Student> crawl(String studentId, String base64Password) throws Exception {
		this.studentId = studentId;
		this.base64Password = base64Password;
		Document doc = Crawler.getStudentDoc("", studentId, base64Password);
		List<Student> result = new ArrayList<>();
		result.addAll(parseDocument(doc));
		return result;
	}

	@Override
	public List<Student> parseDocument(Document doc) throws ParseException {
		Element pic = doc.getElementById(studentId.split("[a-z]")[0]);
		String name = pic.attr("title");
		Student student = new Student();
		student.setId(studentId);
		student.setBase64Password(base64Password);
		student.setName(name);
		List<Student> result = new ArrayList<>();
		result.add(student);
		return result;
	}

}

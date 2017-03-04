package edu.npu.cs595.crawler;

import java.text.ParseException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import edu.npu.cs595.domain.Student;

@Component
@Qualifier("StudentCrawler")
public class StudentCrawlerImpl implements Crawler<Student> {

	@Override
	public List<Student> crawl() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> parseDocument(Document doc) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	public HttpStatus verify(){
		return null;
	}
}

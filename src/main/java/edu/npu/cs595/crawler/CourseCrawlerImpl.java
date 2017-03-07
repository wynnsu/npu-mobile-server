//package edu.npu.cs595.crawler;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import edu.npu.cs595.domain.Course;
//import edu.npu.cs595.domain.Credential;
//
//@Component
//@Qualifier("CourseCrawler")
//public class CourseCrawlerImpl extends Crawler<Course> implements Parser<Course> {
//
//	private final String course_url = "academics/class-schedule";
//	private Date startDate;
//	private Date endDate;
//
//	@Override
//	public List<Course> crawl(Credential credential) throws Exception {
//		Document doc = Crawler.getRelativeDoc(course_url);
//		Element dateElement = doc.getElementById("node-59");
//		Element datePara = dateElement.getElementsByTag("p").first();
//		String dateString = datePara.text();
//		String strStartDate = dateString.split(" ")[2];
//		strStartDate = strStartDate.substring(1);
//		String strEndDate = dateString.split(" ")[4];
//		strEndDate = strEndDate.substring(0, strEndDate.length() - 1);
//		System.out.println(strStartDate);
//		System.out.println(strEndDate);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
//		startDate = dateFormat.parse(strStartDate);
//		endDate = dateFormat.parse(strEndDate);
//
//		Elements links = doc.getElementsByTag("a");
//		String url = "";
//		for (Element l : links) {
//			if (l.text().equals("Class Schedule")) {
//				url = l.attr("href");
//				break;
//			}
//		}
//		List<Course> result = new ArrayList<>();
//		if (url != "") {
//			doc = Crawler.getDoc(url);
//			result.addAll(parseDocument(doc));
//		}
//		return result;
//	}
//
//	@Override
//	public List<Course> parseDocument(Document doc) throws java.text.ParseException {
//		ArrayList<Course> result = new ArrayList();
//		Elements tables = doc.getElementsByClass("Border");
//		for (Element table : tables) {
//			Elements rows = table.getElementsByTag("tr");
//			for (Element row : rows.subList(3, rows.size())) {
//				Elements columns = row.getElementsByTag("td");
//
//				Course course = new Course();
//				course.setCourseNumber(columns.get(1).text());
//				course.setTitle(columns.get(3).text());
//				Calendar cal = Calendar.getInstance();
//				SimpleDateFormat dateFormat = new SimpleDateFormat("EEE h:mm a");
//
//			}
//		}
//		return null;
//	}
//
//	// private final String course_url = "";
//
//	// @Override
//	// public List<Course> crawl() throws Exception {
//	// final String credential = "15325sy:4d5d28022fnpu";
//	// Document doc = Crawler.getDoc(course_url,
//	// Base64.encode(credential.getBytes()));
//	// return parseDocument(doc);
//	// }
//	//
//	// @Override
//	// public List<Course> parseDocument(Document doc) throws ParseException {
//	// ArrayList<Course> result = new ArrayList<>();
//	// System.out.println(doc.html());
//	// Course course=new Course();
//	// course.setId(123);
//	// course.setTitle(doc.html());
//	// result.add(course);
//	// return result;
//	// }
//
//}

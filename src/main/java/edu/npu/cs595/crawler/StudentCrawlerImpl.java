package edu.npu.cs595.crawler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.npu.cs595.dao.CourseDao;
import edu.npu.cs595.dao.StudentDao;
import edu.npu.cs595.domain.Activity;
import edu.npu.cs595.domain.Student;
import edu.npu.cs595.domain.StudentCourse;

@Component
@Qualifier("StudentCrawler")
public class StudentCrawlerImpl extends Crawler<Student> implements Parser<Student> {

	private final String student_url = "/Student";
	private String studentId;
	private String base64Password;

	@Autowired
	StudentDao studentDao;
	@Autowired
	private CourseDao courseDao;
	private Logger logger = Logger.getLogger(StudentCrawlerImpl.class);
	// private Map<String, String> cookies;

	@Override
	public List<Student> crawl(String studentId, String base64Password) throws Exception {
		this.studentId = studentId;
		this.base64Password = base64Password;
		// Document doc = Crawler.getStudentDoc(student_url, studentId,
		// base64Password);
		String password = new String(Base64.getDecoder().decode(base64Password));
		Response response = Jsoup.connect(PORTAL_URL + student_url).method(Method.GET).execute();
		Map<String, String> cookies = response.cookies();
		response = Jsoup.connect(LOGON_URL).cookies(cookies).data("username", studentId).data("password", password)
				.method(Method.POST).execute();
		Document doc = response.parse();
		logger.info("Got student doc: ");
		List<Student> result = new ArrayList<>();
		if (doc == null) {
			logger.info("Doc is null");
			return result;
		}
		result.addAll(parseDocument(doc));
		return result;
	}

	@Override
	public List<Student> parseDocument(Document doc) throws ParseException, IOException {
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
		logger.info("Student Info OK");

		List<StudentCourse> stuCourses = new ArrayList<>();
		Element field = doc.select("fieldset.stuHealth").get(0);
		Element legend = field.getElementsByTag("legend").get(0);
		String[] strs = legend.text().split(" ");
		String semester = strs[1] + strs[0];
		Element table = field.getElementsByTag("table").get(0);
		Elements rows = table.select("tr:gt(0)");
		logger.info("Student Course Found");
		for (Element row : rows) {
			Elements cols = row.select("td");
			StudentCourse stuCourse = new StudentCourse();
			stuCourse.setStudentId(studentId);
			StringBuilder sb = new StringBuilder();
			sb.append(cols.get(1).text());
			if (!cols.get(2).text().isEmpty()) {
				sb.append("-");
				sb.append(cols.get(2).text());
			}
			String courseName = sb.toString();
			int courseId = courseDao.findCourseIdByName(courseName, semester);
			logger.info("Course ID: " + courseId);
			stuCourse.setCourseId(courseId);
			List<Activity> activities = new ArrayList<>();
			String attendance = "";
			if (cols.get(1).text().startsWith("CS595")) {
				attendance = "P P P P P P P P P P";
				Activity act = new Activity();
				act.setTitle("Proposal One Page");
				act.setWeek("W3");
				act.setDue("1/30/2017 12:00:00 AM (Past Due) Allow Late Submission");
				act.setStatus("(Submitted 1/28/2017 11:41:49 AM)");
				act.setPoints("100.00 / 100");
				activities.add(act);

				Activity act2 = new Activity();
				act2.setTitle("Progress Report # 3");
				act2.setWeek("W11");
				act2.setDue("3/28/2017 11:30:00 PM Allow Late Submission");
				act2.setStatus("No answer submitted!");
				act2.setPoints("0 / 100");
				activities.add(act2);
			} else if (cols.get(1).text().startsWith("CS570")) {
				attendance = "P P A P P P P P P P";
				Activity act = new Activity();
				act.setTitle("Midterm");
				act.setWeek("W8");
				act.setDue("3/2/2017 9:00:00 PM (Past Due)");
				act.setStatus("(Submitted 3/2/2017 7:21:08 PM)");
				act.setPoints("20.00 / 20");
				activities.add(act);

				Activity act2 = new Activity();
				act2.setTitle("HW4");
				act2.setWeek("W10");
				act2.setDue("3/26/2017 11:30:00 PM Allow Late Submission ");
				act2.setStatus("No answer submitted!");
				act2.setPoints("0 / 100");
				activities.add(act2);
			}
			stuCourse.setAttendance(attendance);
			StudentCourse savedCourse = studentDao.storeStudentCourse(stuCourse);
			for (Activity act : activities) {
				act.setStudentCourseId(savedCourse.getId());
				studentDao.storeActivity(act);
			}
		}
		List<Student> result = new ArrayList<>();
		result.add(student);
		return result;
	}

	// private List<Activity> getActivities(Document doc) throws IOException {
	// logger.info("Doc Content: " + doc.text());
	// List<Activity> result = new ArrayList<>();
	// Element menu = doc.getElementById("menu");
	// Elements weeks = menu.select("li:gt(0)");
	// logger.info("Activities Found");
	// for (Element week : weeks) {
	// Element weekTab = week.select("a").get(0);
	// Document weekDoc =
	// Jsoup.connect(weekTab.attr("href")).method(Method.GET).execute().parse();
	// List<Activity> acts = getActivity(weekDoc);
	// String weekNo = weekTab.text();
	// for (Activity a : acts) {
	// a.setWeek(weekNo);
	// }
	// result.addAll(getActivity(weekDoc));
	// }
	//
	// return result;
	// }

	// private List<Activity> getActivity(Document doc) {
	// logger.info("Doc Content: " + doc.text());
	// List<Activity> result = new ArrayList<>();
	// Element main = doc.getElementById("main");
	// Elements fields = main.select("fieldset > fieldset");
	// logger.info("Activity Found");
	// for (int i = 0; i < fields.size() / 2; i++) {
	// Activity activity = new Activity();
	// Element detail = fields.get(i);
	// Elements detailRows = detail.select("tr:gt(0)");
	// activity.setTitle(detailRows.get(0).text());
	// Element detailCols = detailRows.select("td > span").get(0);
	// activity.setDue(detailCols.text());
	// Element answer = fields.get(i + 1);
	// Element status = answer.select("legend > span").get(0);
	// activity.setStatus(status.text());
	// Element points = answer.select("b + span").get(0);
	// activity.setPoints(points.text());
	// result.add(activity);
	// }
	// return result;
	// }
	//
	// private String getAttendance(Document doc) {
	// logger.info("Doc Content: " + doc.text());
	// Element container = doc.select("tr[style^=border:2px solid red]").get(0);
	// Elements rows = container.select("td:gt(2)");
	// StringBuilder sb = new StringBuilder();
	// for (int i = 0; i < 15; i++) {
	// sb.append(rows.get(i).text());
	// }
	// return sb.toString();
	// }
}

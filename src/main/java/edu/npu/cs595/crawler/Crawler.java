package edu.npu.cs595.crawler;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawler<E> {
	public static final int SUCCESS = 0;
	public static final int FAILED = 1;
	public static final int DUPLICATE = 2;
	public static final int ERROR = -1;

	// private static Logger logger = Logger.getLogger(Crawler.class);

	static final String BASE_URL = "https://www.npu.edu/";
	static final String LOGON_BASE_URL = "https://my.npu.edu/";
	static final String LOGON_URL = LOGON_BASE_URL + "Account/LogOn";
	static final String STUDENT_URL = LOGON_BASE_URL + "Student";

	public List<E> crawl() throws Exception {
		throw new NotImplementedException("Not implemented");
	}

	public List<E> crawl(String studentId, String base64Password) throws Exception {
		throw new NotImplementedException("Not implemented");
	}

	public static Document getDoc(String url) throws IOException {
		return Jsoup.connect(url).get();
	}

	public static Document getRelativeDoc(String relativeUrl) throws IOException {
		return Jsoup.connect(BASE_URL + relativeUrl).get();
	}

	public static Document getStudentDoc(String url, String studentId, String base64Password) throws IOException {
		// check login status
		Response response = Jsoup.connect(STUDENT_URL + url).method(Method.GET).execute();

		if (response.url().toString().contains(LOGON_URL)) { // If not
			return login(studentId, base64Password).parse(); // login
		} else {
			return null;
		}
	}

	public static Response login(String studentId, String base64Password) throws IOException {
		String password = new String(Base64.getDecoder().decode(base64Password));
		Connection.Response response = Jsoup.connect(LOGON_URL).execute();
		response = Jsoup.connect(LOGON_URL).cookies(response.cookies()).data("username", studentId)
				.data("password", password).method(Method.POST).execute();
		return response;
	}
}

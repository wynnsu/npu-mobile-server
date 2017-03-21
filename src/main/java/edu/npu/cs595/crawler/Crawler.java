package edu.npu.cs595.crawler;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawler<E> {
	public static final int SUCCESS = 0;
	public static final int FAILED = 1;
	public static final int DUPLICATE = 2;
	public static final int ERROR = -1;

	private static Logger logger = Logger.getLogger(Crawler.class);
	private static Map<String, String> cookies = new HashMap<>();
	static final String BASE_URL = "https://www.npu.edu/";
	static final String PORTAL_URL = "https://my.npu.edu";
	static final String LOGON_URL = PORTAL_URL + "/Account/LogOn";
	static final String STUDENT_URL = PORTAL_URL + "/Student";

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
		logger.info("Starting get Student Doc from url: " + PORTAL_URL + url);
		Response response = null;
		response = Jsoup.connect(PORTAL_URL + url).method(Method.GET).execute();
		cookies = response.cookies();
		logger.info("Got response: " + response.url().toString());
		if (response.url().toString().startsWith(LOGON_URL)) { // If not
			logger.info("Not login");
			response = login(studentId, base64Password); // login
		}

		return response.parse();
	}

	public static Response login(String studentId, String base64Password) throws IOException {
		logger.info("Start Login");
		String password = new String(Base64.getDecoder().decode(base64Password));
		// Response response = Jsoup.connect(LOGON_URL).execute();
		Response response = Jsoup.connect(LOGON_URL).cookies(cookies).data("username", studentId)
				.data("password", password).method(Method.POST).execute();
		return response;
	}
}

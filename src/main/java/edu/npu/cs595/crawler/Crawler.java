package edu.npu.cs595.crawler;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.syndication.io.impl.Base64;

import edu.npu.cs595.domain.Credential;

public class Crawler<E> {
	public static final int SUCCESS = 0;
	public static final int FAILED = 1;
	public static final int ERROR = -1;

	static final String BASE_URL = "https://www.npu.edu/";
	static final String LOGON_BASE_URL = "https://my.npu.edu/";
	static final String LOGON_URL = LOGON_BASE_URL + "Account/LogOn";
	static final String STUDENT_URL = LOGON_BASE_URL + "Student";

	public List<E> crawl() throws Exception {
		throw new NotImplementedException("Not implemented");
	}

	public List<E> crawl(Credential credential) throws Exception {
		throw new NotImplementedException("Not implemented");
	}

	public static Document getDoc(String url) throws IOException {
		return Jsoup.connect(url).get();
	}

	public static Document getRelativeDoc(String relativeUrl) throws IOException {
		return Jsoup.connect(BASE_URL + relativeUrl).get();
	}

	public static Document getRelativeDoc(String url, String base64Credential) throws IOException {
		String[] credential = Base64.decode(base64Credential).split(":");
		Connection.Response response = Jsoup.connect(LOGON_URL).execute();
		response = Jsoup.connect(LOGON_URL).cookies(response.cookies()).data("username", credential[0])
				.data("password", credential[1]).method(Method.POST).execute();
		Document doc = Jsoup.connect(LOGON_BASE_URL).cookies(response.cookies()).get();

		return doc;
	}

	public static Document getStudentDoc(String url, Credential credential) throws IOException {
		Connection.Response response = Jsoup.connect(STUDENT_URL).execute();
		if (response.header("Request URL").contains(LOGON_URL)) {
			int status_code = validate(credential.getId(), credential.getBase64Password());

			if (status_code != HttpStatus.SC_MOVED_TEMPORARILY) {
				return null;
			}
			response = Jsoup.connect(STUDENT_URL).execute();
		}
		Document doc = Jsoup.connect(STUDENT_URL).cookies(response.cookies()).get();

		return doc;
	}

	public static int validate(String studentId, String base64Password) throws IOException {
		String password = Base64.decode(base64Password);
		Connection.Response response = Jsoup.connect(LOGON_URL).execute();
		response = Jsoup.connect(LOGON_URL).cookies(response.cookies()).data("username", studentId)
				.data("password", password).method(Method.POST).execute();
		String result = response.url().toString();
		if (result.equals(LOGON_URL)) {
			return SUCCESS;
		} else if (result.equals(STUDENT_URL)) {
			return FAILED;
		} else {
			return ERROR;
		}
	}

}

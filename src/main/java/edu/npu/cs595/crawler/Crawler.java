package edu.npu.cs595.crawler;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.syndication.io.impl.Base64;

public interface Crawler<E> {
	static final String BASE_URL = "https://www.npu.edu/";
	static final String LOGON_BASE_URL = "https://my.npu.edu/";
	static final String LOGON_URL = LOGON_BASE_URL + "Account/LogOn";

	public List<E> crawl() throws Exception;

	public List<E> parseDocument(Document doc) throws ParseException;

	static Document getDoc(String url) throws IOException {
		return Jsoup.connect(BASE_URL + url).get();
	}

	static Document getDoc(String url, String base64Credential) throws IOException {
		String[] credential = Base64.decode(base64Credential).split(":");
		Connection.Response response = Jsoup.connect(LOGON_URL).execute();
		response = Jsoup.connect(LOGON_URL).cookies(response.cookies()).data("username", credential[0])
				.data("password", credential[1]).method(Method.POST).execute();
		Document doc = Jsoup.connect(LOGON_BASE_URL).cookies(response.cookies()).get();

		return doc;
	}

	static int validate(String base64Credential) throws IOException {
		String[] credential = Base64.decode(base64Credential).split(":");
		Connection.Response response = Jsoup.connect(LOGON_URL).execute();
		response = Jsoup.connect(LOGON_URL).cookies(response.cookies()).data("username", credential[0])
				.data("password", credential[1]).method(Method.POST).followRedirects(false).execute();
		return response.statusCode();
	}

}

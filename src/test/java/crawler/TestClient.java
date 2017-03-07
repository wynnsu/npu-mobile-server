package crawler;

import java.util.Map;

import org.jsoup.nodes.Document;

import edu.npu.cs595.crawler.Crawler;
import edu.npu.cs595.domain.Credential;

public class TestClient {


	public static void main(String[] args) {
		try {
			Document doc = Crawler.getStudentDoc("", "15325sy", "NGQ1ZDI4MDIyZm5wdQ==");
			System.out.println(doc.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

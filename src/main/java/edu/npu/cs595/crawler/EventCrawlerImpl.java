package edu.npu.cs595.crawler;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.npu.cs595.domain.AcademicEvent;

@Component
@Qualifier("EventCrawler")
public class EventCrawlerImpl implements Crawler<AcademicEvent> {
	private final String event_url = "/academics/academic-calendar";

	private List<AcademicEvent> getEvents(Document pageDoc) throws ParseException {
		ArrayList<AcademicEvent> result = new ArrayList<>();

		Elements dayList = pageDoc.getElementsByClass("day");
		Elements monthList = pageDoc.getElementsByClass("month");
		Elements contentList = pageDoc.getElementsByClass("ca_content");

		int numOfEvent = contentList.size();
		if ((numOfEvent == monthList.size()) && (numOfEvent == dayList.size())) {
			for (int i = 0; i < numOfEvent; i++) {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat inputMonthFormat = new SimpleDateFormat("MMM");
				cal.setTime(inputMonthFormat.parse(monthList.get(i).text()));
				Year year = Year.now();
				if (cal.get(Calendar.MONTH) < Calendar.getInstance().get(Calendar.MONTH)) {
					year = Year.now().plusYears(1);
				}
				SimpleDateFormat outputMonthFormat = new SimpleDateFormat("MM");
				String date = year + "-" + outputMonthFormat.format(cal.getTime()) + "-" + dayList.get(i).text();
				result.add(new AcademicEvent(Date.valueOf(date), contentList.get(i).text()));
			}
		}
		return result;
	}

	@Override
	public List<AcademicEvent> crawl() throws Exception {
		Document doc = Crawler.getDoc(event_url);
		Elements pages = doc.getElementsByClass("pager-item");
		List<AcademicEvent> result = new ArrayList<>();
		result.addAll(getEvents(doc));
		for (Element page : pages) {
			doc=Crawler.getDoc( page.getElementsByTag("a").attr("href"));
			result.addAll(getEvents(doc));
		}
		return result;
	}
}

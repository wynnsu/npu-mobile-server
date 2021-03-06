package edu.npu.cs595.crawler;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.npu.cs595.domain.AcademicEvent;

@Component
@Qualifier("EventCrawler")
public class EventCrawlerImpl extends Crawler<AcademicEvent> implements Parser<AcademicEvent> {
	private final String event_url = "/academics/academic-calendar";

	@Override
	public List<AcademicEvent> crawl() throws Exception {
		Document doc = Crawler.getRelativeDoc(event_url);
		Elements pages = doc.getElementsByClass("pager-item");
		List<AcademicEvent> result = new ArrayList<>();
		result.addAll(parseDocument(doc));
		for (Element page : pages) {
			doc = Crawler.getRelativeDoc(page.getElementsByTag("a").attr("href"));
			result.addAll(parseDocument(doc));
		}
		return result;
	}

	@Override
	public List<AcademicEvent> parseDocument(Document doc) throws ParseException {
		ArrayList<AcademicEvent> result = new ArrayList<>();

		Elements dayList = doc.getElementsByClass("day");
		Elements monthList = doc.getElementsByClass("month");
		Elements contentList = doc.getElementsByClass("ca_content");

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
}

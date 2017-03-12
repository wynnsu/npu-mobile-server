package edu.npu.cs595.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.npu.cs595.crawler.Crawler;
import edu.npu.cs595.dao.AcademicEventDao;
import edu.npu.cs595.domain.AcademicEvent;

@Service
public class AcademicEventServiceImpl implements AcademicEventService {

	@Autowired
	@Qualifier("AcademicEventDaoHibernate")
	private AcademicEventDao eventDao;

	@Autowired
	@Qualifier("EventCrawler")
	private Crawler<AcademicEvent> eventCrawler;

	protected static Logger logger = Logger.getLogger(AcademicEventService.class);

	@Override
	public List<AcademicEvent> getEvents() {
		return eventDao.findAllEvents();
	}

	@Override
	public AcademicEvent getEventById(int eventId) {
		return eventDao.findEvent(eventId);
	}

	// Fire at 7:00 AM everyday
	@Scheduled(cron = "0 0 7 * * ?")
	// @Scheduled(cron="15 * * * * ?")
	@Override
	public void updateEventList() {
		try {
			logger.info("Retrieving data");
			List<AcademicEvent> list = eventCrawler.crawl();
			eventDao.removeAll();
			eventDao.storeEventList(list);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

}

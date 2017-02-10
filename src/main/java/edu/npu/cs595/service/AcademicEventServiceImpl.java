package edu.npu.cs595.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.cs595.dao.AcademicEventDao;
import edu.npu.cs595.domain.AcademicEvent;

@Service
public class AcademicEventServiceImpl implements AcademicEventService {

	@Autowired
	@Qualifier("AcademicEventDaoHibernate")
	private AcademicEventDao eventDao;

	@Override
	public List<AcademicEvent> getEvents() {
		return eventDao.findAllEvents();
	}

	@Override
	public AcademicEvent getEventById(int eventId) {
		return eventDao.findEvent(eventId);
	}

}

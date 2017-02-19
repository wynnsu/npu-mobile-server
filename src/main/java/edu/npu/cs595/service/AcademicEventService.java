package edu.npu.cs595.service;

import java.util.List;

import edu.npu.cs595.domain.AcademicEvent;

public interface AcademicEventService {
	public List<AcademicEvent> getEvents();

	public AcademicEvent getEventById(int eventId);

	public void updateEventList();
}

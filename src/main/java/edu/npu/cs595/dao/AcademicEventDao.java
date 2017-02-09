package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.AcademicEvent;;

public interface AcademicEventDao {
	public AcademicEvent storeEvent(AcademicEvent event);

	public AcademicEvent findEvent(int eventId);

	public void removeEvent(AcademicEvent event);

	public List<AcademicEvent> findAllEvents();
}

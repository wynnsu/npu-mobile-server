package edu.npu.cs595.resthandler;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import edu.npu.cs595.domain.*;
import edu.npu.cs595.service.*;
import edu.npu.cs595.exceptions.*;

@Path("/")
public class NPUMobileRestHandler {
	@Autowired
	private AcademicEventService eventService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private NewsService newsService;
	private Logger logger = Logger.getLogger(NPUMobileRestHandler.class);

	@GET
	@Path("/event/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AcademicEvent getEvent(@PathParam("id") int id) {
		AcademicEvent event = null;
		try {
			event = eventService.getEventById(id);
		} catch (Exception e) {
			throw new WebApplicationException(e.getMessage());
		}
		if (event == null) {
			logger.debug("Failed request to lookup event with id: " + id);
			throw new UnknownResourceException("Event id: " + id + " is invalid");
		}
		return event;
	}

	@GET
	@Path("/event")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AcademicEvent> getEventList() {
		List<AcademicEvent> eventList = eventService.getEvents();
		return eventList;
	}

	@GET
	@Path("/building/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Building getBuilding(@PathParam("id") int id) {
		Building building = null;
		try {
			building = buildingService.getBuildingById(id);
		} catch (Exception e) {
			throw new WebApplicationException(e.getMessage());
		}
		if (building == null) {
			logger.debug("Failed request to lookup building with id: " + id);
			throw new UnknownResourceException("Building id: " + id + " is invalid");
		}
		return building;
	}

	@GET
	@Path("/building")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Building> getBuildingList() {
		List<Building> buildingList = buildingService.getBuildings();
		return buildingList;
	}

	@GET
	@Path("/news/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public News getNews(@PathParam("id") int id) {
		News news = null;
		try {
			news = newsService.getNewsById(id);
		} catch (Exception e) {
			throw new WebApplicationException(e.getMessage());
		}
		if (news == null) {
			logger.debug("Failed request to lookup news with id: " + id);
			throw new UnknownResourceException("News id: " + id + " is invalid");
		}
		return news;
	}

	@GET
	@Path("/news")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getNewsList() {
		List<News> newsList = newsService.getNews();
		return newsList;
	}

	@GET
	@Path("/course/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourseById(@PathParam("id") int id) {
		Course course = null;
		try {
			course = courseService.getCourseById(id);
		} catch (Exception e) {
			throw new WebApplicationException(e.getMessage());
		}
		if (course == null) {
			logger.debug("Failed request to lookup course with id: " + id);
			throw new UnknownResourceException("Course id: " + id + " is invalid");
		}
		return course;

	}

	@GET
	@Path("/course")
	public List<Course> getCourses() {
		List<Course> courseList = courseService.getCourses();
		return courseList;
	}

	@GET
	@Path("/course/{id}/prerequisite")
	public List<Course> getPrerequisite(@PathParam("id") int id) {
		Course course = null;
		try {
			course = courseService.getCourseById(id);
		} catch (Exception e) {
			throw new WebApplicationException(e.getMessage());
		}
		if (course == null) {
			logger.debug("Failed request to lookup course with id: " + id);
			throw new UnknownResourceException("Course id: " + id + " is invalid");
		}
		return course.getPrerequisite();
	}
	// @GET
	// @Path("/player/{id}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Player getPlayer(@PathParam("id") int id) {
	// Player player = null;
	//
	// try {
	// player = playerService.getPlayerById(id);
	// } catch (Exception ex) {
	// throw new WebApplicationException(ex.getMessage());
	// }
	//
	// if (player == null) {
	// logger.debug("Failed request to lookup player with id :" + id);
	// throw new UnknownResourceException("Player id: " + id + " is invalid");
	// }
	//
	// return player;
	// }
}

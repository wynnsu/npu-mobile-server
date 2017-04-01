package edu.npu.cs595.resthandler;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import edu.npu.cs595.domain.AcademicEvent;
import edu.npu.cs595.domain.Activity;
import edu.npu.cs595.domain.Building;
import edu.npu.cs595.domain.Course;
import edu.npu.cs595.domain.News;
import edu.npu.cs595.domain.Student;
import edu.npu.cs595.domain.StudentCourse;
import edu.npu.cs595.exceptions.UnknownResourceException;
import edu.npu.cs595.service.AcademicEventService;
import edu.npu.cs595.service.BuildingService;
import edu.npu.cs595.service.CourseService;
import edu.npu.cs595.service.NewsService;
import edu.npu.cs595.service.StudentService;

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
	@Autowired
	private StudentService studentService;
	private Logger logger = Logger.getLogger(NPUMobileRestHandler.class);

	@GET
	@Path("/event/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AcademicEvent getEventById(@PathParam("id") int id) {
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
	@Path("/event/latest")
	@Produces(MediaType.APPLICATION_JSON)
	public AcademicEvent getEventLatest() {
		AcademicEvent event = null;
		try {
			event = eventService.getEventLatest();
		} catch (Exception e) {
			throw new WebApplicationException(e.getMessage());
		}
		if (event == null) {
			logger.debug("Failed request to lookup latest event");
			throw new UnknownResourceException("No valid event");
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
	public Building getBuildingById(@PathParam("id") int id) {
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
	@Path("/news/latest")
	@Produces(MediaType.APPLICATION_JSON)
	public News getNewsLatest() {
		News news = null;
		try {
			news = newsService.getNewsLatest();
		} catch (Exception e) {
			throw new WebApplicationException(e.getMessage());
		}
		if (news == null) {
			logger.debug("Failed request to lookup latest news");
			throw new UnknownResourceException("No valid News");
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
	@Path("/course/{id}/suggest")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourseSuggested(@PathParam("id") String studentId) {
		Course course = null;
		try {
			course = courseService.getCourseSuggested(studentId);
		} catch (Exception e) {
			throw new WebApplicationException(e.getMessage());
		}
		if (course == null) {
			logger.debug("Failed request to lookup suggested course for student: " + studentId);
			throw new UnknownResourceException("Suggested Course for: " + studentId + " is unkonwn");
		}
		return course;
	}

	@GET
	@Path("/course")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseList() {
		List<Course> courseList = courseService.getCourses();
		return courseList;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/student/{id}")
	public Student registerStudent(@PathParam("id") String studentId, String params) {
		Student student = null;
		JSONObject obj = new JSONObject(params);
		student = studentService.registerStudent(studentId, obj.getString("password"));
		if (student == null) {
			logger.debug("Failed request to register student with id: " + studentId);
			throw new UnknownResourceException("Student id: " + studentId + " is invalid");
		}
		return student;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/student/{id}")
	public Student getStudentById(@PathParam("id") String studentId) {
		Student student = new Student();
		student = studentService.getStudentById(studentId);
		return student;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/student/{id}/activity")
	public List<Activity> getActivityListById(@PathParam("id") String studentId) {
		List<Activity> result = studentService.getActivityById(studentId);
		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/student/{id}/activity/coming")
	public List<Activity> getActivityComing(@PathParam("id") String studentId) {
		List<Activity> activity = studentService.getActivityComing(studentId);
		return activity;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/student/{id}/activity/latest")
	public List<Activity> getActivityLatest(@PathParam("id") String studentId) {
		List<Activity> result = studentService.getActivityLatest(studentId);
		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/student/{id}/attendance")
	public Response getAttendance(@PathParam("id") String studentId) {
		JSONArray result = new JSONArray();
		List<StudentCourse> courses = studentService.getAttendance(studentId);
		for (StudentCourse sc : courses) {
			JSONObject obj = new JSONObject();
			Course course = courseService.getCourseById(sc.getCourseId());
			obj.put("title",course.getCourseNumber());
			obj.put("attendance", sc.getAttendance());
			result.put(obj);
		}
		return Response.ok(result.toList()).build();
	}

	@PUT
	@Path("/update")
	public Response updateGeneral() {
		eventService.updateEventList();
		newsService.updateNewsList();
		courseService.updateCourseList();
		return Response.ok().build();
	}

}

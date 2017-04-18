package edu.npu.cs595.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.npu.cs595.crawler.Crawler;
import edu.npu.cs595.dao.CourseDao;
import edu.npu.cs595.domain.Course;
import edu.npu.cs595.domain.CourseId;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	@Qualifier("CourseDaoHibernate")
	private CourseDao courseDao;

	@Autowired
	@Qualifier("CourseCrawler")
	private Crawler<Course> courseCrawler;

	protected static Logger logger = Logger.getLogger(CourseService.class);

	@Override
	public Course getCourseById(CourseId courseId) {
		return courseDao.findCourse(courseId);
	}

	@Override
	public List<Course> getCourses() {
		return courseDao.findAllCourses();
	}

	// Fire at 7:00 AM on first day of month
	@Scheduled(cron = "0 0 7 1 * ?")
	// @Scheduled(cron = "0/30 * * * * ?")
	@Override
	public void updateCourseList() {
		try {
			logger.info("Retrieving data: " + courseCrawler.toString());
			List<Course> list = courseCrawler.crawl();
			logger.info("After retrieving");
			logger.info("Data: " + list.size());
			courseDao.removeAll();
			courseDao.storeCourseList(list);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Override
	public Course getCourseSuggested(String studentId) {
		return courseDao.findSuggested(studentId);
	}

	@Override
	public String getCurrentSemester() {
		return courseDao.getCurrentSemester();
	}

	@Override
	public int getCurrentWeek() {
		return courseDao.getCurrentWeek();
	}

}

package edu.npu.cs595.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.npu.cs595.domain.Course;
import edu.npu.cs595.dao.CourseDao;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	@Qualifier("CourseDao")
	private CourseDao courseDao;

	@Override
	public Course getCourseById(int courseId) {
		return courseDao.findCourse(courseId);
	}

	@Override
	public List<Course> getCourses() {
		return courseDao.findAllCourses();
	}

	@Override
	public List<Course> getPrerequisite(int courseId) {
		return courseDao.findCourse(courseId).getPrerequisite();
	}

}

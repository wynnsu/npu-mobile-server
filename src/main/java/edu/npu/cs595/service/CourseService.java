package edu.npu.cs595.service;

import java.util.List;

import edu.npu.cs595.domain.Course;
import edu.npu.cs595.domain.CourseId;

public interface CourseService {
	public Course getCourseById(CourseId courseId);

	public List<Course> getCourses();

	public void updateCourseList();

	public Course getCourseSuggested(String studentId);

	public String getCurrentSemester();

	public int getCurrentWeek();
}

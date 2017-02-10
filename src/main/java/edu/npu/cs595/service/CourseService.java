package edu.npu.cs595.service;

import java.util.List;

import edu.npu.cs595.domain.Course;

public interface CourseService {
	public Course getCourseById(int courseId);

	public List<Course> getCourses();

	public List<Course> getPrerequisite(int courseId);
}

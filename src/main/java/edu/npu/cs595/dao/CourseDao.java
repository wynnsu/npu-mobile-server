package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.Course;

public interface CourseDao {

	public Course storeCourse(Course course);

	public Course findCourse(int courseId);

	public void removeCourse(Course course);

	public List<Course> findAllCourses();
}

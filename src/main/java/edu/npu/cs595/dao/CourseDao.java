package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.Course;

public interface CourseDao {

	public void storeCourseList(List<Course> list);

	public Course storeCourse(Course course);

	public Course findCourse(int courseId);

	public void removeCourse(Course course);

	public List<Course> findAllCourses();

	public void removeAll();

	public Course findSuggested(String studentId);

	public int findCourseIdByName(String string,String semester);
}

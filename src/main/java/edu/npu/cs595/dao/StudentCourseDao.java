package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.StudentCourse;

public interface StudentCourseDao {
	public StudentCourse storeStudentCourse(StudentCourse studentCourse);

	public StudentCourse findStudentCourse(int studentCourseId);

	public void removeStudentCourse(StudentCourse studentCourse);

	public List<StudentCourse> findStudentCourseByStudentId(String studentId);

	public void storeStudentCourseList(List<StudentCourse> list);
}

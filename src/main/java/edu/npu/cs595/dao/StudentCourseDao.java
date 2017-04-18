package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.Enroll;

public interface StudentCourseDao {
	public Enroll storeStudentCourse(Enroll studentCourse);

	public Enroll findStudentCourse(int studentCourseId);

	public void removeStudentCourse(Enroll studentCourse);

	public List<Enroll> findStudentCourseByStudentId(String studentId);

	public void storeStudentCourseList(List<Enroll> list);
}

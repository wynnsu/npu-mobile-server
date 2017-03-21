package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.Activity;
import edu.npu.cs595.domain.Student;
import edu.npu.cs595.domain.StudentCourse;

public interface StudentDao {
	public Student storeStudent(Student student);

	public Student findStudent(String studentId);

	public void removeStudent(Student student);

	public List<StudentCourse> findStudentCourseByStudentId(String studentId);

	public StudentCourse storeStudentCourse(StudentCourse studentCourse);

	public Activity storeActivity(Activity activity);

	public List<Activity> findActivity(String studentId);

	public List<String> findAttendance(String studentId);

	public String findGradeLatest(String studentId);

	public Activity findActivityComing(String studentId);
}

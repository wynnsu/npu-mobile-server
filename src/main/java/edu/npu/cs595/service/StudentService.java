package edu.npu.cs595.service;

import java.util.List;

import edu.npu.cs595.domain.Activity;
import edu.npu.cs595.domain.Student;
import edu.npu.cs595.domain.StudentCourse;

public interface StudentService {
	public Student getStudentById(String studentId);

	public Student registerStudent(String studentId, String base64Password);

	public void updateStudent(String studentId) throws Exception;

	public List<Activity> getActivityById(String studentId);

	public List<StudentCourse> getAttendance(String studentId);

	public List<Activity> getActivityLatest(String studentId);

	public List<Activity> getActivityComing(String studentId);
}

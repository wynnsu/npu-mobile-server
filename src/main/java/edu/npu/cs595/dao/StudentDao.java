package edu.npu.cs595.dao;

import java.util.List;

import edu.npu.cs595.domain.Activity;
import edu.npu.cs595.domain.Student;
import edu.npu.cs595.domain.StudentCourse;

/**
 * @author su153
 *
 */
public interface StudentDao {
	public Student storeStudent(Student student);

	public Student findStudent(String studentId);

	public void removeStudent(Student student);

	public List<StudentCourse> findStudentCourseByStudentId(String studentId);

	/**
	 * @param studentId
	 * @param code
	 *            0 for all, -1 for latest, 1 for coming
	 * @return
	 */
	public List<Activity> findActivity(String studentId, int code);

	public List<StudentCourse> findAttendance(String studentId);
}

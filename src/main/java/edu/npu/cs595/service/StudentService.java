package edu.npu.cs595.service;

import edu.npu.cs595.domain.Student;

public interface StudentService {
	public Student getStudentById(String studentId);

	public Student registerStudent(String studentId, String base64Password);
}

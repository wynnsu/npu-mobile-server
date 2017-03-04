package edu.npu.cs595.service;

import java.io.IOException;

import edu.npu.cs595.domain.Student;

public interface StudentService {
	public Student getStudentById(int studentId);

	public int addNewStudent(Student student) throws IOException;

	public void removeStudent(Student student);
}

package edu.npu.cs595.dao;

import edu.npu.cs595.domain.Student;

public interface StudentDao {
	public Student storeStudent(Student student);
	public Student findStudent(String studentId);
	public void removeStudent(Student student);
}
